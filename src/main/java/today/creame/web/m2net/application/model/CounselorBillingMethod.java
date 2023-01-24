package today.creame.web.m2net.application.model;

import lombok.Getter;

public enum CounselorBillingMethod {

    PRE("Y", "선불"),
    POST("N", "후불"),
    ALL("P", "");

    @Getter
    private String code;
    @Getter
    private String label;

    CounselorBillingMethod(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
