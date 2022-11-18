package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class ExceededFailedCountException extends BusinessException {

    private final static MemberErrorCode errorCode = MemberErrorCode.EXCEEDED_FAILED_COUNT;

    public ExceededFailedCountException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
