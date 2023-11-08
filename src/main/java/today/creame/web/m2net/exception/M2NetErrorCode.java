package today.creame.web.m2net.exception;

import lombok.Getter;

public enum M2NetErrorCode {

    CONFLICT_REGISTER_MEMBER(5000, "M2net 회원등록에 실패하였습니다. "),
    NOT_ENOUGH_BALANCE(5001, "잔액이 부족합니다."),

    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    M2NetErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}