package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class AlreadyVerifiedTokenException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.VERIFIED_TOKEN;

    public AlreadyVerifiedTokenException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
