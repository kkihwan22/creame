package today.creame.web.influence.exception;

import static today.creame.web.influence.exception.InfluenceErrorCode.NOT_FOUND_GREETING_HISTORY;

import today.creame.web.share.exception.BusinessException;

public class NotFoundGreetingsHistoryException extends BusinessException {

    private final static InfluenceErrorCode errorCode = NOT_FOUND_GREETING_HISTORY;

    public NotFoundGreetingsHistoryException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
