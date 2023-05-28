package today.creame.web.member.social.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundSocialTokenException extends BusinessException {
    private final static SocialErrorCode errorCode = SocialErrorCode.NOT_FOUNT_SOCIAL_TOKEN;

    public NotFoundSocialTokenException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
