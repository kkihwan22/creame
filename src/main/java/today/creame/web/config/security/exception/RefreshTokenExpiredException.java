package today.creame.web.config.security.exception;

import static today.creame.web.config.security.exception.SecurityErrorCode.EXPIRED_REFRESH_TOKEN;

import lombok.Getter;

public class RefreshTokenExpiredException extends RuntimeException {
    @Getter
    private final static SecurityErrorCode errorCode = EXPIRED_REFRESH_TOKEN;
}
