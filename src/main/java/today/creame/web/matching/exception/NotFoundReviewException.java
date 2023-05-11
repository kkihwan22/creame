package today.creame.web.matching.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundReviewException extends BusinessException {

    private final static MatchingErrorCode errorCode = MatchingErrorCode.NOT_FOUND_REVIEW;

    public NotFoundReviewException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
