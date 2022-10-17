package today.creame.web.common.exception;

import static today.creame.web.common.exception.FileResourceErrorCode.UPLOAD_FAILURE;

import today.creame.web.share.exception.BusinessException;

public class UploadFailureException extends BusinessException {
    private final static FileResourceErrorCode errorCode = UPLOAD_FAILURE;

    public UploadFailureException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
