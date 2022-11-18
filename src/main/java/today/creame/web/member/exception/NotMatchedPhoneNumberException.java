package today.creame.web.member.exception;

import static today.creame.web.member.exception.MemberErrorCode.NOT_MATCHED_PHONE_NUMBER;

import today.creame.web.share.exception.BusinessException;

public class NotMatchedPhoneNumberException extends BusinessException {
    private final static MemberErrorCode errorCode = NOT_MATCHED_PHONE_NUMBER;

    public NotMatchedPhoneNumberException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
