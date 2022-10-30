package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class ConflictConnectionStatusException extends BusinessException {

    private final static InfluenceErrorCode errorCode = InfluenceErrorCode.CONFLICT_CONNECTION_STATUS;

    public ConflictConnectionStatusException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
