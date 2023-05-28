package today.creame.web.member.social.exception;

import today.creame.web.share.exception.BusinessException;

public class AppleTokenDecodeFailException extends BusinessException {

    private final static SocialErrorCode errorCode = SocialErrorCode.APPLE_TOKEN_DECODE_FAIL_EXCEPTION;

    public AppleTokenDecodeFailException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
