package today.creame.web.share.domain;

import lombok.Getter;

public enum OnOffCondition {
    ON(Boolean.TRUE), OFF(Boolean.FALSE);

    @Getter
    private Boolean condition;

    OnOffCondition(Boolean condition) {
        this.condition = condition;
    }
}
