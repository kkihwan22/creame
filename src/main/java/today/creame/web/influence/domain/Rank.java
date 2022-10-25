package today.creame.web.influence.domain;

import lombok.Getter;

public enum Rank {
    RED("5단계"),

    ;

    @Getter
    private String label;

    Rank(String label) {
        this.label = label;
    }
}
