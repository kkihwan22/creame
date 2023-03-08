package today.creame.web.m2net.exception;

import static today.creame.web.m2net.exception.M2NetErrorCode.CONFLICT_REGISTER_MEMBER;

import today.creame.web.share.exception.BusinessException;

public class ConflictMemberException extends BusinessException {

    private final static M2NetErrorCode errorCode = CONFLICT_REGISTER_MEMBER;

    public ConflictMemberException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
