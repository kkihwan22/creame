package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundHotInfluencerException extends BusinessException {

    private final static InfluenceErrorCode errorCode = InfluenceErrorCode.NOT_FOUND_HOTINFLUENCER;

    public NotFoundHotInfluencerException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
