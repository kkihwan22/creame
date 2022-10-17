package today.creame.web.common.exception;

import static today.creame.web.common.exception.FileResourceErrorCode.EXCEED_FILE_COUNT;

import today.creame.web.share.exception.BusinessException;

public class ExceedFileCountException extends BusinessException {
    private final static FileResourceErrorCode errorCode = EXCEED_FILE_COUNT;

    public ExceedFileCountException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
