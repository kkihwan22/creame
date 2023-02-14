package today.creame.web.m2net.domain;

import lombok.Getter;

public enum CounselorStatus {

    IDLE("상담가능"), ABSE("부재중"), CONN("상담중");

    @Getter
    private String label;

    CounselorStatus(String label) {
        this.label = label;
    }
}
