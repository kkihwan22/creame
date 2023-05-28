package today.creame.web.member.social.exception;

import lombok.Getter;

public enum SocialErrorCode {

    NOT_FOUND_SOCIAL_USER_INFO(6000, "소셜에서 유저정보를 찾을 수 없습니다."),
    NOT_FOUND_SOCIAL_MEMBER(6001, "등록된 소셜 회원을 찾을 수 없습니다."),
    NOT_MATCH_SOCIAL_TYPE(6002, "이미 가입된 소셜 타입과 다릅니다."),
    NOT_FOUNT_SOCIAL_TOKEN(6003, "소셜 토큰을 찾을 수 없습니다."),
    APPLE_PRIVATE_KEY_NOT_FOUND(6004, "Apple Private key를 찾을 수 없습니다."),
    APPLE_PRIVATE_KEY_PARSER_FAIL(6005, "Apple Private key를 파싱하는데 실패하였습니다."),
    APPLE_TOKEN_DECODE_FAIL_EXCEPTION(6006, "Apple token을 decode 하는데 실패하였습니다.")

    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    SocialErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
