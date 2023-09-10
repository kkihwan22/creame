package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.matching.applicaton.model.MatchingStatisticsResult;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
public class MatchingStatisticsDetailResponse {
    private String yearMonth;
    private int postTime;
    private long postCoin;
    private int preTime;
    private long preCoin;
    private int totalTime;
    private long totalCoin;

    public MatchingStatisticsDetailResponse(String yearMonth, List<MatchingStatisticsResult> preResults, List<MatchingStatisticsResult> postResults) {
        this.yearMonth = yearMonth;
        if (!preResults.isEmpty()) {
            MatchingStatisticsResult preResult = preResults.get(0);
            this.preCoin = preResult.getTotalCoin();
            this.preTime = preResult.getTotalTime();
        }

        if (!postResults.isEmpty()) {
            MatchingStatisticsResult postResult = postResults.get(0);
            this.postCoin = postResult.getTotalCoin();
            this.postTime = postResult.getTotalTime();
        }

        this.totalCoin = postCoin + preCoin;
        this.totalTime = postTime + preTime;
    }
}