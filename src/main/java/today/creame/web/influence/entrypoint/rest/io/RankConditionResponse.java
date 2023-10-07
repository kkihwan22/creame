package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.application.model.RankConditionResult;
import today.creame.web.influence.domain.Rank;

@Getter @ToString
public class RankConditionResponse {
    private String currentRankName;
    private int currentRankOrder;
    private String nextRankName;
    private int nextRankOrder;
    private Long nextRankAmount;
    private int consecutiveNumber;
    private int meetConditionCount;

    public RankConditionResponse(RankConditionResult result) {
        Rank rank = result.getRank();
        this.currentRankName = rank.getLabel();
        this.currentRankOrder = rank.ordinal();

        Rank nextRank = rank.getNextRank();
        this.nextRankName = nextRank.getLabel();
        this.nextRankOrder = nextRank.ordinal();
        this.nextRankAmount = rank.getNextRankAmount();

        this.consecutiveNumber = result.getConsecutiveNumber();
        this.meetConditionCount = result.getMeetConditionCount();
    }
}
