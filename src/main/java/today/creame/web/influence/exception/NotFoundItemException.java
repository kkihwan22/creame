package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundItemException extends BusinessException {

    private final static InfluenceErrorCode errorCode = InfluenceErrorCode.NOT_FOUND_INFLUENCE;

    public NotFoundItemException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
