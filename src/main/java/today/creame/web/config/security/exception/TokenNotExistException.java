package today.creame.web.config.security.exception;

import static today.creame.web.config.security.exception.SecurityErrorCode.NOT_EXIST_TOKEN;

import lombok.Getter;
import today.creame.web.share.exception.BusinessException;

public class TokenNotExistException extends BusinessException {
    @Getter
    private final static SecurityErrorCode errorCode = NOT_EXIST_TOKEN;

    public TokenNotExistException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
