package today.creame.web.matching.exception;

import today.creame.web.member.exception.MemberErrorCode;
import today.creame.web.share.exception.BusinessException;

public class NotFoundMatchingException extends BusinessException {

    private final static MemberErrorCode errorCode = MemberErrorCode.NOT_FOUND_MEMBER;

    public NotFoundMatchingException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
