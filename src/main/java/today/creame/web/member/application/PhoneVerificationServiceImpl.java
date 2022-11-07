package today.creame.web.member.application;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import today.creame.web.member.application.support.RandomNumberSupport;
import today.creame.web.member.domain.PhoneVerification;
import today.creame.web.member.domain.PhoneVerificationJpaRepository;
import today.creame.web.member.exception.ExpiredVerifyTokenException;
import today.creame.web.member.exception.NotMatchedVerifyCodeException;
import today.creame.web.member.exception.NotMatchedVerifyTokenException;
import today.creame.web.sms.entrypoint.listener.event.SmsSendEvent;

@RequiredArgsConstructor
@Service
public class PhoneVerificationServiceImpl implements PhoneVerificationService {
    private final Logger log = LoggerFactory.getLogger(PhoneVerificationServiceImpl.class);
    private final PhoneVerificationJpaRepository phoneVerificationJpaRepository;
    private final ApplicationEventPublisher publisher;


    @Transactional(dontRollbackOn = {
            ExpiredVerifyTokenException.class, NotMatchedVerifyCodeException.class
    })
    @Override
    public void verifyCode(String token, String phoneNumber, String code) {
        PhoneVerification phoneVerification = phoneVerificationJpaRepository
                .findById(Long.parseLong(token))
                .orElseThrow(() -> {
                    log.info("Not matched token. token: {}", token);
                    throw new NotMatchedVerifyTokenException();
                });

        // TODO: refactoring -> entity 내부로 로직 이동!
        if (phoneVerification.isExpired() || phoneVerification.isVerified()) {
            log.info("Already expired or verified token. expired status: {}, verified status: {}"
                    , phoneVerification.isExpired()
                    , phoneVerification.isVerified());
            throw new ExpiredVerifyTokenException();
        }

        if (phoneVerification.afterVerifyTime()) {
            log.info("Expired token. [ reason ] - time over!");
            throw new ExpiredVerifyTokenException();
        }

        if (phoneVerification.isMissMatchDigit(Integer.parseInt(code))) {
            log.info("Not Matched verify code. request verify code: {}, saved verify code: {}", code, phoneVerification.getDigit());
            throw new NotMatchedVerifyCodeException();
        }

        if (!phoneVerification.getPhoneNumber().equals(phoneNumber)) {
            log.info("Not matched phone number. request phone number: {}, saved phone number : {}"
                    , phoneNumber
                    , phoneVerification.getPhoneNumber());
            throw new NotMatchedVerifyTokenException();
        }

        phoneVerification.successVerify();
    }

    @Transactional
    @Override
    public Long requestCode(String phoneNumber) {
        int digit = RandomNumberSupport.random6Digit();
        log.debug("digit: {}", digit);

        // DB 저장
        PhoneVerification phoneVerification = phoneVerificationJpaRepository.save(new PhoneVerification(phoneNumber, digit));
        log.debug("saved phoneVerification: {}", phoneVerification);
        publisher.publishEvent(new SmsSendEvent(phoneNumber, Integer.toString(digit)));

        return phoneVerification.getId();
    }
}