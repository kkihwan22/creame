package today.creame.web.share.aspect.permit.exception;

import today.creame.web.share.exception.BusinessException;

public class NoPermitException extends BusinessException {

    private static PermitErrorCode errorCode = PermitErrorCode.NO_PERMIT;

    public NoPermitException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
