package today.creame.web.config.security.exception;

import static today.creame.web.config.security.exception.SecurityErrorCode.EXPIRED_REFRESH_TOKEN;

import lombok.Getter;
import today.creame.web.share.exception.BusinessException;

public class RefreshTokenExpiredException extends BusinessException {
    @Getter
    private final static SecurityErrorCode errorCode = EXPIRED_REFRESH_TOKEN;

    public RefreshTokenExpiredException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
