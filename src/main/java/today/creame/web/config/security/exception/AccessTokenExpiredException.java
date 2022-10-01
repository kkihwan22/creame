package today.creame.web.config.security.exception;

import lombok.Getter;

import static today.creame.web.config.security.exception.SecurityErrorCode.EXPIRED_ACCESS_TOKEN;

public class AccessTokenExpiredException extends RuntimeException {
    @Getter
    private final static SecurityErrorCode errorCode = EXPIRED_ACCESS_TOKEN;
}
