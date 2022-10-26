package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class ConflictMemberPhoneNumberException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.CONFLICT_MEMBER_PHONE_NUMBER;

    public ConflictMemberPhoneNumberException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
