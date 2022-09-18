package today.creame.web.member.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundMemberException extends BusinessException {

    private final static MemberErrorCode errorCode = MemberErrorCode.NOT_FOUND_MEMBER;

    public NotFoundMemberException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
