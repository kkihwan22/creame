package today.creame.web.matching.exception;

import today.creame.web.share.exception.BusinessException;

public class ConflictReviewClaimException extends BusinessException {

    private final static MatchingErrorCode errorCode = MatchingErrorCode.CONFLICT_REVIEW_CLAIM;

    public ConflictReviewClaimException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
