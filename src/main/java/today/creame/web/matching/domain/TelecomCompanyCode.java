package today.creame.web.matching.domain;

import lombok.Getter;

public enum TelecomCompanyCode {

    TEL_O60("060"), TEL_070("070");

    @Getter
    private String label;

    TelecomCompanyCode(String label) {
        this.label = label;
    }
}
