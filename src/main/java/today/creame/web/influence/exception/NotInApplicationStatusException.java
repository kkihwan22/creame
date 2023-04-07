package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class NotInApplicationStatusException extends BusinessException {

    private final static InfluenceErrorCode errorCode = InfluenceErrorCode.NOT_IN_REQUEST_STATUS;

    public NotInApplicationStatusException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
