package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;
import today.creame.web.share.exception.model.ErrorBodyData;
import today.creame.web.share.exception.model.MetaBodyData;

import java.util.List;

public class BadRequestAnswerException extends BusinessException {

    private static InfluenceErrorCode errorCode = InfluenceErrorCode.BAD_REQUEST_ANSWER;

    public BadRequestAnswerException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
