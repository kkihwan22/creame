package today.creame.web.share.support;

import today.creame.web.config.security.exception.InvalidTokenException;

public class BearerTokenSupporter {

    public static String extract(String token) {
        if (!token.startsWith("Bearer ")) {
            throw new InvalidTokenException();
        }

        return token.substring("Bearer ".length());
    }
}
