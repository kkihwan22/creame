package today.creame.web.ranking.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundRankingException extends BusinessException {

    private final static RankingErrorCode errorCode = RankingErrorCode.NOT_FOUND_RANKING;

    public NotFoundRankingException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
