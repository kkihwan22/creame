package today.creame.web.sms.infra.support;

import java.util.Random;

public class SaltSupport {

    public static String generate() {
        int randomNumberOrigin = 48;
        int randomNumberBound = 122;
        return new Random()
                .ints(randomNumberOrigin, randomNumberBound)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(12)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
