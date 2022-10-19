package today.creame.web.influence.exception;

import lombok.Getter;

public enum InfluenceApplicationErrorCode {


    NOT_FOUND_APPLICATION(2000, "인플루언스 요청서를 찾을 수 없습니다.")
    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    InfluenceApplicationErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}