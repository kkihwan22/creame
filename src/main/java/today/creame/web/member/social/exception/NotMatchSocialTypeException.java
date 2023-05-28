package today.creame.web.member.social.exception;


import today.creame.web.share.exception.BusinessException;

public class NotMatchSocialTypeException extends BusinessException {
    private final static SocialErrorCode errorCode = SocialErrorCode.NOT_MATCH_SOCIAL_TYPE;

    public NotMatchSocialTypeException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
