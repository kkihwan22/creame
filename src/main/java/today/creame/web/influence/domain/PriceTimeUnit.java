package today.creame.web.influence.domain;

import lombok.Getter;

public enum PriceTimeUnit {

    SEC("second", "초"), MIN("minute", "분"), HOUR("hour", "시간")

    ;

    @Getter
    private String code;
    @Getter
    private String label;

    PriceTimeUnit(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
