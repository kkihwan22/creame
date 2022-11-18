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
import today.creame.web.member.exception.AlreadyExpiredTokenException;
import today.creame.web.member.exception.NotMatchedCodeException;
import today.creame.web.member.exception.NotMatchedTokenException;
import today.creame.web.sms.entrypoint.listener.event.SmsSendEvent;

@RequiredArgsConstructor
@Service
public class PhoneVerificationServiceImpl implements PhoneVerificationService {
    private final Logger log = LoggerFactory.getLogger(PhoneVerificationServiceImpl.class);
    private final PhoneVerificationJpaRepository phoneVerificationJpaRepository;
    private final ApplicationEventPublisher publisher;


    @Transactional(dontRollbackOn = {
        AlreadyExpiredTokenException.class, NotMatchedCodeException.class
    })
    @Override
    public void verifyCode(String token, String phoneNumber, String code) {
        PhoneVerification phoneVerification = phoneVerificationJpaRepository
            .findByToken(token)
                .orElseThrow(() -> {
                    log.info("Not matched token. token: {}", token);
                    throw new NotMatchedTokenException();
                });

        phoneVerification.verity(phoneNumber, Integer.parseInt(code));
    }

    @Transactional
    @Override
    public Long requestCode(String phoneNumber) {
        int digit = RandomNumberSupport.random6Digit();
        long token = RandomNumberSupport.random10Digit();
        log.debug("digit: {}, token: {}", digit, token);

        // DB 저장
        PhoneVerification phoneVerification = phoneVerificationJpaRepository.save(new PhoneVerification(phoneNumber, digit, token));
        log.debug("saved phoneVerification: {}", phoneVerification);
        publisher.publishEvent(new SmsSendEvent(phoneNumber, String.valueOf(digit)));

        return token;
    }
}