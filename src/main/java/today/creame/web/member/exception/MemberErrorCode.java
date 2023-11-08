package today.creame.web.member.exception;

import lombok.Getter;

public enum MemberErrorCode {

    NOT_MATCHED_TOKEN(1000, "토큰 정보가 일치하지 않습니다."),
    EXPIRED_TOKEN(1001, "토큰이 만료되었습니다."),
    NOT_MATCHED_VERIFY_CODE(1002, "인증번호가 일치하지 않습니다."),
    NOT_FOUND_MEMBER(1003, "등록된 회원을 찾을 수 없습니다."),
    CONFLICT_MEMBER_EMAIL(1004, "Email 중복입니다."),
    CONFLICT_MEMBER_NICKNAME(1005, "닉네임 중복입니다."),
    CONFLICT_MEMBER_PHONE_NUMBER(1006, "휴대전화 번호 중복입니다."),
    VERIFIED_TOKEN(1007, "이미 검증 된 토큰입니다."),
    NOT_MATCHED_DIGIT_CODE(1008, "인증번호가 일치하지 않습니다."),
    NOT_MATCHED_PHONE_NUMBER(1009, "휴대전화 번호가 일치하지 않습니다."),
    EXCEEDED_FAILED_COUNT(1010, "인증번호 확인 시도가 초과했습니다."),
    FORBIDDEN(1011, "허용되지 않는 요청입니다."),
    INVALID_TOKEN(1012, "인증 되지 않은 토큰입니다.")
    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    MemberErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}