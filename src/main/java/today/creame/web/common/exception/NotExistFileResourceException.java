package today.creame.web.common.exception;

import static today.creame.web.common.exception.FileResourceErrorCode.NOT_EXIST_FILE_RESOURCE;

import today.creame.web.share.exception.BusinessException;

public class NotExistFileResourceException extends BusinessException {
    private final static FileResourceErrorCode errorCode = NOT_EXIST_FILE_RESOURCE;

    public NotExistFileResourceException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
