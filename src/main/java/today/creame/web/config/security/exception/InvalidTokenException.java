package today.creame.web.config.security.exception;

import static today.creame.web.config.security.exception.SecurityErrorCode.EXPIRED_ACCESS_TOKEN;

import lombok.Getter;
import today.creame.web.share.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    @Getter
    private final static SecurityErrorCode errorCode = EXPIRED_ACCESS_TOKEN;

    public InvalidTokenException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
