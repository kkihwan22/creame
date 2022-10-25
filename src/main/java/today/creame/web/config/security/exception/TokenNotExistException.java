package today.creame.web.config.security.exception;

import static today.creame.web.config.security.exception.SecurityErrorCode.NOT_EXIST_TOKEN;

import lombok.Getter;

public class TokenNotExistException extends RuntimeException {
    @Getter
    private final static SecurityErrorCode errorCode = NOT_EXIST_TOKEN;
}
