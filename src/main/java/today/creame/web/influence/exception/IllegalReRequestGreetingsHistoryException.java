package today.creame.web.influence.exception;

import static today.creame.web.influence.exception.InfluenceErrorCode.ILLEGAL_RE_REQUEST_GREETING_HISTORY;

import today.creame.web.share.exception.BusinessException;

public class IllegalReRequestGreetingsHistoryException extends BusinessException {

    private final static InfluenceErrorCode errorCode = ILLEGAL_RE_REQUEST_GREETING_HISTORY;

    public IllegalReRequestGreetingsHistoryException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
