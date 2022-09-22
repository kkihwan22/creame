package today.creame.web.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    private final Logger log = LoggerFactory.getLogger(PasswordEncoderTest.class);

    @Test
    void test() {

        String password = "1234";

        String encode = new BCryptPasswordEncoder().encode(password);

        log.debug("encode password : {}", encode);
    }
}
