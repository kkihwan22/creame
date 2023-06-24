package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class MatchingStatisticsDetailResponse {
    private String yearMonth;
    private LocalTime postTime;
    private Long postCoin;
    private LocalTime preTime;
    private Long preCoin;
    private LocalTime totalTime;
    private Long totalCoin;

    public MatchingStatisticsDetailResponse(String yearMonth, LocalTime postTime, Long postCoin, LocalTime preTime, Long preCoin, LocalTime totalTime, Long totalCoin) {
        this.yearMonth = yearMonth;
        this.postTime = postTime;
        this.postCoin = postCoin;
        this.preTime = preTime;
        this.preCoin = preCoin;
        this.totalTime = totalTime;
        this.totalCoin = totalCoin;
    }
}