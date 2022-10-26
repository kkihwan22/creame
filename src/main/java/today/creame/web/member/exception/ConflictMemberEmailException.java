package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class ConflictMemberEmailException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.CONFLICT_MEMBER_EMAIL;

    public ConflictMemberEmailException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
