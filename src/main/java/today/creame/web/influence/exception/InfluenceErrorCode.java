package today.creame.web.influence.exception;

import lombok.Getter;

public enum InfluenceErrorCode {


    NOT_FOUND_INFLUENCE_APPLICATION(2000, "인플루언스 요청서를 찾을 수 없습니다."),
    NOT_FOUND_INFLUENCE(2001, "해당 인플루언스를 찾을 수 없습니다."),
    CONFLICT_CONNECTION_STATUS(2002, "이미 반영된 상태를 요청하셨습니다."),
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