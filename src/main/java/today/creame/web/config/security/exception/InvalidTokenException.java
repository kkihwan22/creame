package today.creame.web.config.security.exception;

import static today.creame.web.config.security.exception.SecurityErrorCode.EXPIRED_ACCESS_TOKEN;

import lombok.Getter;

public class InvalidTokenException extends RuntimeException {
    @Getter
    private final static SecurityErrorCode errorCode = EXPIRED_ACCESS_TOKEN;
}
