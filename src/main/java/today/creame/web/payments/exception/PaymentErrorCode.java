package today.creame.web.payments.exception;

import lombok.Getter;

public enum PaymentErrorCode {

    ISSUE_BILL_KEY(7000, "빌키 발급 중 에러가 발생했습니다."),
    ILLEGAL_CREDIT_CARD_DATA(7001, "등록할 카드의 정보가 일치하지 않습니다."),
    CONFLICT_CREDIT_CARD(7002, "이미 등록 된 카드가 있습니다.");

    @Getter
    private final int code;
    @Getter
    private final String message;

    PaymentErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}