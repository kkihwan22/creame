package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import today.creame.web.matching.domain.PaidType;
import today.creame.web.matching.entrypoint.rest.io.MatchingStatisticsResponse;
import java.time.LocalTime;

@Getter
public class MatchingStatisticsResult {
    private String yearMonth;
    private PaidType paidType;
    private LocalTime totalTime;
    private Long totalCoin;


    public MatchingStatisticsResult(String yearMonth, PaidType paidType, LocalTime totalTime, Long totalCoin) {
        this.yearMonth = yearMonth;
        this.paidType = paidType;
        this.totalTime = totalTime;
        this.totalCoin = totalCoin;
    }

    public MatchingStatisticsResult(Object[] objects) {
        this.yearMonth = objects[0].toString();
        this.paidType = PaidType.valueOf(objects[1].toString());
        this.totalTime = LocalTime.parse(objects[2].toString());
        this.totalCoin = Long.valueOf(objects[3].toString());
    }
}