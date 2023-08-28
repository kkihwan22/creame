package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class BadRequestBannerImageException extends BusinessException {

    private static InfluenceErrorCode errorCode = InfluenceErrorCode.BAD_REQUEST_BANNER_IMAGE;

    public BadRequestBannerImageException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
