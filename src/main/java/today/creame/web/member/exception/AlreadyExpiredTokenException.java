package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class AlreadyExpiredTokenException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.EXPIRED_TOKEN;

    public AlreadyExpiredTokenException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
