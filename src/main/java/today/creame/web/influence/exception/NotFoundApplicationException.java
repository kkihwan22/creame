package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundApplicationException extends BusinessException {

    private final static InfluenceApplicationErrorCode errorCode = InfluenceApplicationErrorCode.NOT_FOUND_APPLICATION;

    public NotFoundApplicationException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
