package today.creame.web.config.security.exception;

import lombok.Getter;

public class TokenExpiredException extends RuntimeException {
    @Getter
    private final static SecurityErrorCode errorCode = SecurityErrorCode.EXPIRED_TOKEN;
}
