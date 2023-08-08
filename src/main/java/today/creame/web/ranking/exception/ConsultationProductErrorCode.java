package today.creame.web.ranking.exception;

import lombok.Getter;

public enum ConsultationProductErrorCode {

    NOT_FOUND_CONSULTATION_PRODUCT(6000, "통화 상품 정보를 찾을 수 없습니다."),
    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    ConsultationProductErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}