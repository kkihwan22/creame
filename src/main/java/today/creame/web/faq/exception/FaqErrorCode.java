package today.creame.web.faq.exception;

import lombok.Getter;

public enum FaqErrorCode {
    NOT_FOUND_FAQ(8000, "FAQ 정보를 찾을 수 없습니다."),
    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    FaqErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
