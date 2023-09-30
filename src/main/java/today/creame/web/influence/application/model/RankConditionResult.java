package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.Rank;

@Getter @ToString
public class RankConditionResult {
    private Rank rank;
    private int consecutiveNumber;
    private int meetConditionCount;

    public RankConditionResult(Influence influence, int consecutiveNumber, int meetConditionCount) {
        this.rank = influence.getRank();
        this.consecutiveNumber = consecutiveNumber;
        this.meetConditionCount = meetConditionCount;
    }
}
