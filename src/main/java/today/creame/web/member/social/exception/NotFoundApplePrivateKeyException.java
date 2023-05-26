package today.creame.web.member.social.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundApplePrivateKeyException extends BusinessException {

    private final static SocialErrorCode errorCode = SocialErrorCode.APPLE_PRIVATE_KEY_NOT_FOUND;

    public NotFoundApplePrivateKeyException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
