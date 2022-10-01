package today.creame.web.config.security.exception;

import lombok.Getter;

import static today.creame.web.config.security.exception.SecurityErrorCode.EXPIRED_ACCESS_TOKEN;
import static today.creame.web.config.security.exception.SecurityErrorCode.NOT_EXIST_TOKEN;

public class TokenNotExistException extends RuntimeException {
    @Getter
    private final static SecurityErrorCode errorCode = NOT_EXIST_TOKEN;
}
