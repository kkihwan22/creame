package today.creame.web.matching.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundMatchingStatisticsException extends BusinessException {

    private final static MatchingErrorCode errorCode = MatchingErrorCode.NOT_FOUND_MATCHING_STATISTICS;

    public NotFoundMatchingStatisticsException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
