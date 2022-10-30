package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundInfluenceException extends BusinessException {

    private final static InfluenceErrorCode errorCode = InfluenceErrorCode.NOT_FOUND_INFLUENCE;

    public NotFoundInfluenceException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
