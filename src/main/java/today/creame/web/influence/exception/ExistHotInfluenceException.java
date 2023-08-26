package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class ExistHotInfluenceException extends BusinessException {

    private static InfluenceErrorCode errorCode = InfluenceErrorCode.EXIST_HOT_INFLUENCE;

    public ExistHotInfluenceException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
