package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.application.model.RankConditionResult;
import today.creame.web.influence.domain.Rank;

import java.util.Optional;

@Getter @ToString
public class RankConditionResponse {
    private String currentRankName;
    private int currentRankOrder;
    private String nextRankNameName;
    private int nextRankOrder;
    private Long nextRankAmount;
    private int consecutiveNumber;
    private int meetConditionCount;

    public RankConditionResponse(RankConditionResult result) {
        Rank rank = result.getRank();
        this.currentRankName = rank.getLabel();
        this.currentRankOrder = rank.ordinal();

        Optional.ofNullable(rank.getNextRank()).ifPresent(item -> {
            this.nextRankNameName = item.getLabel();
            this.nextRankOrder = item.ordinal();
            this.nextRankAmount = item.getNextRankAmount();
        });

        this.consecutiveNumber = result.getConsecutiveNumber();
        this.meetConditionCount = result.getMeetConditionCount();
    }
}
