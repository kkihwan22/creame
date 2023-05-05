package today.creame.web.payments.domain;

import lombok.Getter;

public enum PaymentMethod {
    CARD("신용카드"),
    KAKAO("카카오페이"),
    NAVER("네이버페이"),
    PAYCO("페이코"),
    VACCT("가상계좌"),

    ;
    @Getter
    private String label;

    PaymentMethod(String label) {
        this.label = label;
    }
}
