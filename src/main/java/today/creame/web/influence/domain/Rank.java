package today.creame.web.influence.domain;

import lombok.Getter;

public enum Rank {
    WHITE("화이트"),
    BLUE("블루"),
    RED("레드"),
    PURPLE("퍼플"),
    BLACK("블랙"),

    ;

    @Getter
    private String label;

    Rank(String label) {
        this.label = label;
    }
}
