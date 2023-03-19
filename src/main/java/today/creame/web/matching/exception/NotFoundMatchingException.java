package today.creame.web.matching.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundMatchingException extends BusinessException {

    private final static MatchingErrorCode errorCode = MatchingErrorCode.NOT_FOUND_MATCHING;

    public NotFoundMatchingException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
