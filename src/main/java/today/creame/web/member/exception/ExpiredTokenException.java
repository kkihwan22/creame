package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class ExpiredTokenException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.EXPIRED_TOKEN;

    public ExpiredTokenException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
