package today.creame.web.influence.domain;

import lombok.Getter;

import java.util.Arrays;

public enum Category {

    MENTALITY("심리"),
    BEAUTY("뷰티/패션"),
    BABY("육아"),
    DATING("연애"),
    TARO("사주/타로"),
    GAME("게임"),
    SPORT("운동"),
    TRAVEL("여행"),
    FINANCE("금융"),
    LIFE("일상/기타"),
    PET("반려동물"),
    HEALTH("건강"),
    HOROSCOPE("사주")  //기존 데이터 때문에 오류 발생 하여 지우지 않음
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
