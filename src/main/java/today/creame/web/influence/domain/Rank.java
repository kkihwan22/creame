package today.creame.web.influence.domain;

import lombok.Getter;

public enum Rank {
    WHITE("0단계"),
    YELLOW("1단계"),
    GREEN("2단계"),
    BLUE("3단계"),
    ORANGE("4단계"),
    RED("5단계"),
    PURPLE("6단계"),

    ;

    @Getter
    private String label;

    Rank(String label) {
        this.label = label;
    }
}
