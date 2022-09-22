package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class ExpiredVerifyTokenException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.EXPIRED_TOKEN;

    public ExpiredVerifyTokenException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
