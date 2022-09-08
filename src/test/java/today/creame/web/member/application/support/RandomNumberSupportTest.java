package today.creame.web.member.application.support;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class RandomNumberSupportTest {
    private final static Logger log = LoggerFactory.getLogger(RandomNumberSupportTest.class);

    @Test
    void test_generateRandom6Digit() {
        int count = 0;
        while (count < 1000) {
            int actual = RandomNumberSupport.random6Digit();
            log.debug("actual:{}", actual);
            assertTrue(actual >= 100000);
            assertTrue(actual < 1000000);
            count++;
        }
    }

}