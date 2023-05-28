package today.creame.web.member.social.exception;


import today.creame.web.share.exception.BusinessException;

public class NotFoundProviderProfileException extends BusinessException {
    private final static SocialErrorCode errorCode = SocialErrorCode.NOT_FOUND_SOCIAL_USER_INFO;

    public NotFoundProviderProfileException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
