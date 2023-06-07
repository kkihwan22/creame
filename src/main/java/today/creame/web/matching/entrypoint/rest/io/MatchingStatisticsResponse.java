package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.matching.domain.PaidType;

import java.time.LocalTime;


@Getter
public class MatchingStatisticsResponse {
    private String yearMonth;
    private PaidType paidType;
    private LocalTime totalTime;
    private Long totalCoin;


    public MatchingStatisticsResponse(String yearMonth, PaidType paidType, LocalTime totalTime, Long totalCoin) {
        this.yearMonth = yearMonth;
        this.paidType = paidType;
        this.totalTime = totalTime;
        this.totalCoin = totalCoin;
    }
}
