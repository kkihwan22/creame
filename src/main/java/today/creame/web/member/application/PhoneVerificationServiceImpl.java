package today.creame.web.member.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import today.creame.web.member.application.model.SmsSendEvent;
import today.creame.web.member.application.support.RandomNumberSupport;
import today.creame.web.member.domain.PhoneVerification;
import today.creame.web.member.domain.PhoneVerificationJpaRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PhoneVerificationServiceImpl implements PhoneVerificationService {
    private final Logger log = LoggerFactory.getLogger(PhoneVerificationServiceImpl.class);
    private final PhoneVerificationJpaRepository phoneVerificationJpaRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    @Override
    public String requestCode(String phoneNumber) {
        int digit = RandomNumberSupport.random6Digit();
        log.debug("digit: {}", digit);

        // DB 저장
        PhoneVerification phoneVerification = phoneVerificationJpaRepository.save(new PhoneVerification(phoneNumber, digit));
        log.debug("saved phoneVerification: {}", phoneVerification);
        publisher.publishEvent(new SmsSendEvent(phoneNumber, Integer.toString(digit)));
        return null;
    }
}