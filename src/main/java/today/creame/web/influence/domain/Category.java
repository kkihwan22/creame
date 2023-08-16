package today.creame.web.influence.domain;

import lombok.Getter;

import java.util.Arrays;

public enum Category {

    MENTALITY("심리"),
    BEAUTY("뷰티"),
    BABY("육아"),
    DATING("연애"),
    HOROSCOPE("사주"),
    TARO("타로"),
    GAME("게임"),
    SPORT("게임"),
    TRAVEL("여행"),
    FINANCE("금융"),
    LIFE("일상"),
    PET("반려동물"),
    HEALTH("건강")
    ;

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
