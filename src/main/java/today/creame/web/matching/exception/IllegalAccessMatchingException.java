package today.creame.web.matching.exception;

import today.creame.web.share.exception.BusinessException;

public class IllegalAccessMatchingException extends BusinessException {

    private final static MatchingErrorCode errorCode = MatchingErrorCode.ILLEGAL_ACCESS_MATCHING;

    public IllegalAccessMatchingException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
