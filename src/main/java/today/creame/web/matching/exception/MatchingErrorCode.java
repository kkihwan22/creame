package today.creame.web.matching.exception;

import lombok.Getter;

public enum MatchingErrorCode {

    NOT_FOUND_MATCHING(4000, "해당 매칭 정보를 찾을 수 없습니다."),

    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    MatchingErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}