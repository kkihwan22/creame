package today.creame.web.influence.exception;

import static today.creame.web.influence.exception.InfluenceErrorCode.CONFLICT_BOOKMARK_REQUEST;

import today.creame.web.share.exception.BusinessException;

public class ConflictBookmarkException extends BusinessException {
    private static final InfluenceErrorCode errorCode = CONFLICT_BOOKMARK_REQUEST;

    public ConflictBookmarkException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
