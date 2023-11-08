package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.INVALID_TOKEN;

    public InvalidTokenException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
