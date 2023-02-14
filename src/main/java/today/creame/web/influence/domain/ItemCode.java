package today.creame.web.influence.domain;

import lombok.Getter;

public enum ItemCode {

    COIN("070"), POST("060"),
    ;

    @Getter
    private String label;

    ItemCode(String label) {
        this.label = label;
    }
}