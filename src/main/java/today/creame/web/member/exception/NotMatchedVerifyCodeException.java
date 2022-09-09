package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class NotMatchedVerifyCodeException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.NOT_MATCHED_TOKEN;

    public NotMatchedVerifyCodeException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
