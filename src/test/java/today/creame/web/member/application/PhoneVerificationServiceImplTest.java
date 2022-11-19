package today.creame.web.member.application;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import today.creame.web.member.domain.PhoneVerification;
import today.creame.web.member.domain.PhoneVerificationJpaRepository;

@ExtendWith(MockitoExtension.class)
class PhoneVerificationServiceImplTest {

    PhoneVerificationService verificationService;

    @Mock
    PhoneVerificationJpaRepository phoneVerificationJpaRepository;

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    private PhoneVerification given;

    @BeforeEach
    public void setup() {

        verificationService = new PhoneVerificationServiceImpl(
                phoneVerificationJpaRepository,
                applicationEventPublisher
        );

        given = new PhoneVerification(
                10L,
                "01039960399",
                123456,
                Boolean.FALSE,
                Boolean.FALSE,
                0,
                null,
                LocalDateTime.now().minusMinutes(2),
                LocalDateTime.now());
    }

    @Test
    public void test_verify_code_success() {
        // given
        when(
                phoneVerificationJpaRepository.findById(10L)
        ).thenReturn(
                Optional.of(given)
        );

        verificationService.verify("10", "01039960399", "123456");
    }
}