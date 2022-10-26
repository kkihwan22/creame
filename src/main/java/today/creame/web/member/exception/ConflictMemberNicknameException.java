package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class ConflictMemberNicknameException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.CONFLICT_MEMBER_NICKNAME;

    public ConflictMemberNicknameException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
