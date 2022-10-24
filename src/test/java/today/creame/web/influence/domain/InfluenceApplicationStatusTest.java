package today.creame.web.influence.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InfluenceApplicationStatusTest {

    @Test
    void test_not_match_value() {

        InfluenceApplicationStatus.valueOf("OK");
    }

}