package today.creame.web.member.application;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
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
import today.creame.web.share.event.SmsSendEvent;

@RequiredArgsConstructor
@Service
public class PhoneVerificationServiceImpl implements PhoneVerificationService {
    private final Logger log = LoggerFactory.getLogger(PhoneVerificationServiceImpl.class);
    private final PhoneVerificationJpaRepository phoneVerificationJpaRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional(
        dontRollbackOn = {
            AlreadyExpiredTokenException.class,
            NotMatchedCodeException.class
        })
    @Override
    public void verify(String token, String phoneNumber, Integer code) {
        PhoneVerification phoneVerification = phoneVerificationJpaRepository
            .findPhoneVerificationByPhoneNumberAndToken(phoneNumber, token)
            .orElseThrow(() -> {
                log.info("Not matched token. token: {}", token);
                throw new NotMatchedTokenException();
            });

        phoneVerification.verity(phoneNumber, code);
    }

    @Transactional
    @Override
    public String requestCode(String phoneNumber) {
        List<PhoneVerification> results = phoneVerificationJpaRepository
            .findPhoneVerificationsByPhoneNumberAndVerifiedAndCreatedDateTimeAfter(phoneNumber, false, LocalDateTime.now().minusMinutes(3));
        log.debug("results: {}", results);

        if (results.isEmpty()) {
            PhoneVerification phoneVerification = this.createPhoneVerification(phoneNumber);
            log.debug("new request code: {}, token: {}", phoneVerification.getDigit(), phoneVerification.getToken());
            phoneVerificationJpaRepository.save(phoneVerification);
            publisher.publishEvent(new SmsSendEvent(phoneNumber, String.valueOf(phoneVerification.getDigit())));
            return phoneVerification.getToken();
        }

        PhoneVerification maxPhoneVerification = results.stream()
            .max(Comparator.comparing(PhoneVerification::getCreatedDateTime))
            .orElseGet(null);

        log.debug("recently time code: {}, token: {}", maxPhoneVerification.getDigit(), maxPhoneVerification.getToken());

        return maxPhoneVerification.getToken();
    }

    @Override
    public boolean isVerified(String token) {
        List<PhoneVerification> results = phoneVerificationJpaRepository.findPhoneVerificationsByTokenAndVerified(token, true);
        if (results.isEmpty()) {
            log.info("Not matched token. token: {}", token);
            throw new NotMatchedTokenException();
        }

        boolean expired = !results.stream().anyMatch(result -> result.getCreatedDateTime().plusMinutes(5).isAfter(LocalDateTime.now()));
        if (expired) {
            log.error("인증 유효시간이 지났습니다.");
            throw new AlreadyExpiredTokenException();
        }
        return true;
    }

    private PhoneVerification createPhoneVerification(String phoneNumber) {
        int digit = RandomNumberSupport.random6Digit();
        long token = RandomNumberSupport.random10Digit();
        log.debug("digit: {}, token: {}", digit, token);
        return new PhoneVerification(phoneNumber, digit, String.valueOf(token));
    }
}