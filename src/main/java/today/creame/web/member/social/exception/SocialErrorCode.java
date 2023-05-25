package today.creame.web.member.social.exception;

import lombok.Getter;

public enum SocialErrorCode {

    APPLE_PRIVATE_KEY_NOT_FOUND(6000, "Apple Private key를 찾을 수 없습니다."),
    APPLE_PRIVATE_KEY_PARSER_FAIL(6001, "Apple Private key를 파싱하는데 실패하였습니다.")

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
