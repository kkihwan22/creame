package today.creame.web.matching.exception;

import today.creame.web.share.exception.BusinessException;

public class DuplicateReviewException extends BusinessException {

    private final static MatchingErrorCode errorCode = MatchingErrorCode.DUPLICATE_REVIEW;

    public DuplicateReviewException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
