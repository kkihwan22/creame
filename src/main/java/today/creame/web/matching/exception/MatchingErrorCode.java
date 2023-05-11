package today.creame.web.matching.exception;

import lombok.Getter;

public enum MatchingErrorCode {

    NOT_FOUND_MATCHING(4000, "해당 매칭 정보를 찾을 수 없습니다."),
    ILLEGAL_ACCESS_MATCHING(4001, "해당 사용자와 관계없는 매칭입니다."),
    NOT_FOUND_REVIEW(4002, "존재하지 않는 리뷰입니다."),

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