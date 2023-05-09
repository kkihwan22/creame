package today.creame.web.member.social.exception;


import today.creame.web.member.exception.MemberErrorCode;
import today.creame.web.share.exception.BusinessException;

public class NotFoundProviderProfileException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.NOT_FOUND_SOCIAL_USER_INFO;

    public NotFoundProviderProfileException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
