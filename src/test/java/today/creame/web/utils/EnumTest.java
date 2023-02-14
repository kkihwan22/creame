package today.creame.web.utils;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

public class EnumTest {

    @Ignore
    @Test
    void test_not_match_value() {

        EnumTestCode cookie = EnumTestCode.valueOf("COOKIE");
    }

    public enum EnumTestCode {

        CANDY, CHOCOLATE, SNACK
    }
}
