package today.creame.web.influence.exception;

import today.creame.web.share.exception.BusinessException;

public class BadRequestException extends BusinessException {

    private static InfluenceErrorCode errorCode = InfluenceErrorCode.BAD_REQUEST;

    public BadRequestException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
