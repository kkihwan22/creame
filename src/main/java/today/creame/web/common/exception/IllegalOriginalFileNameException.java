package today.creame.web.common.exception;

import static today.creame.web.common.exception.FileResourceErrorCode.ILLEGAL_FILENAME;

import today.creame.web.share.exception.BusinessException;

public class IllegalOriginalFileNameException extends BusinessException {
    private final static FileResourceErrorCode errorCode = ILLEGAL_FILENAME;

    public IllegalOriginalFileNameException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
