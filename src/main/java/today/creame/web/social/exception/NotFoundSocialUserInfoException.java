package today.creame.web.social.exception;


import today.creame.web.member.exception.MemberErrorCode;
import today.creame.web.share.exception.BusinessException;

public class NotFoundSocialUserInfoException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.NOT_FOUND_SOCIAL_USER_INFO;

    public NotFoundSocialUserInfoException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
