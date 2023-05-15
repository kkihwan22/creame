package today.creame.web.influence.exception;

import static today.creame.web.influence.exception.InfluenceErrorCode.NOT_FOUND_QUESTION;

import today.creame.web.share.exception.BusinessException;

public class NotFoundQnAException extends BusinessException {

    private final static InfluenceErrorCode errorCode = NOT_FOUND_QUESTION;

    public NotFoundQnAException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
