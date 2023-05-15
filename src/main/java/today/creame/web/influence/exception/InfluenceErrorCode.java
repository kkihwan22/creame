package today.creame.web.influence.exception;

import lombok.Getter;

public enum InfluenceErrorCode {

    NOT_FOUND_INFLUENCE_APPLICATION(2000, "인플루언스 요청서를 찾을 수 없습니다."),
    NOT_FOUND_INFLUENCE(2001, "해당 인플루언스를 찾을 수 없습니다."),
    CONFLICT_CONNECTION_STATUS(2002, "이미 반영된 상태를 요청하셨습니다."),
    CONFLICT_BOOKMARK_REQUEST(2003, "북마크 할 수 없습니다."),
    NOT_FOUND_QUESTION(2004, "존재하지 않는 문의입니다."),
    NOT_FOUND_GREETING_HISTORY(2005, "인사말 요청이력을 찾을 수 없습니다."),
    ILLEGAL_RE_REQUEST_GREETING_HISTORY(2006, "인사말을 재요청할 수 없습니다."),
    NOT_IN_REQUEST_STATUS(2007, "인플루언스 신청(REQUEST) 상태가 아닙니다."),
    BAD_REQUEST_ANSWER(2008, "답변을 달 수 없는 게시물입니다.")


    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    InfluenceErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}