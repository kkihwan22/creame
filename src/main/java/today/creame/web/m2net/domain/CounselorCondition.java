package today.creame.web.m2net.domain;

import lombok.Getter;

public enum CounselorCondition {

    IDLE("상담가능"), ABSE("부재중"), CONN("상담중");

    @Getter
    private String label;

    CounselorCondition(String label) {
        this.label = label;
    }
}
