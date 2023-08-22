package today.creame.web.faq.domain;

import lombok.Getter;

public enum FaqCategory {
    MEMBER("회원정보"), SERVICE("이용안내"), PAY("결제/환불"), ETC("기타")

    ;

    @Getter
    private String value;

    FaqCategory(String value) {
        this.value = value;
    }
}
