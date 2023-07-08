package today.creame.web.influence.domain;

import lombok.Getter;

import java.util.Arrays;

public enum Category {

    MENTALITY("심리"),
    BEAUTY("뷰티"),
    BABY("육아"),
    DATING("연애"),
    HOROSCOPE("사주"),
    TARO("타로");

    @Getter
    private String label;

    Category(String label) {
        this.label = label;
    }

    public static Category valueOfLabel(String label) {
        return Arrays.stream(values())
                .filter(value -> value.label.equals(label))
                .findAny()
                .orElse(null);
    }
}
