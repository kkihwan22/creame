package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class BadRequestProfileImageSizeOverException extends BusinessException {

    private static InfluenceErrorCode errorCode = InfluenceErrorCode.BAD_REQUEST_PROFILE_IMAGE_SIZE_OVER;

    public BadRequestProfileImageSizeOverException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
