package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundApplicationException extends BusinessException {

    private final static InfluenceErrorCode errorCode = InfluenceErrorCode.NOT_FOUND_INFLUENCE_APPLICATION;

    public NotFoundApplicationException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
