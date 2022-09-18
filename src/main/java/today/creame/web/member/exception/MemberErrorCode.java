package today.creame.web.member.exception;

import lombok.Getter;

public enum MemberErrorCode {

    NOT_MATCHED_TOKEN (1000, "토큰 정보가 유효하지 않습니다."),
    EXPIRED_TOKEN(1001, "토큰이 만료되었습니다."),
    NOT_MATCHED_VERIFY_CODE(1002, "인증번호가 일치하지 않습니다."),
    NOT_FOUND_MEMBER(1003, "등록된 회원을 찾을 수 없습니다.")
    ;

    @Getter
    private int code;
    @Getter
    private String message;

    MemberErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}