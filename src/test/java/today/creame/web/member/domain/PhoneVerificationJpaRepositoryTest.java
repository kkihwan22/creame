package today.creame.web.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@AutoConfigureTestDatabase(replace = NONE)
@DataJpaTest
class PhoneVerificationJpaRepositoryTest {
    private final Logger log = LoggerFactory.getLogger(PhoneVerificationJpaRepositoryTest.class);

    @Autowired
    private PhoneVerificationJpaRepository phoneVerificationJpaRepository;

    @Test
    @DisplayName("저장하기")
    void test_savePhoneAuthentication() {

        String phoneNumber = "01045671234";
        int digit = 156553;

        PhoneVerification save = phoneVerificationJpaRepository.save(new PhoneVerification(phoneNumber, digit));
        log.debug("saved: {}", save);
        phoneVerificationJpaRepository.flush();

        Optional<PhoneVerification> byPhoneNumber = phoneVerificationJpaRepository.findByPhoneNumber(phoneNumber);

        assertNotNull(byPhoneNumber.get());
        assertEquals(save, byPhoneNumber.get());
    }
}