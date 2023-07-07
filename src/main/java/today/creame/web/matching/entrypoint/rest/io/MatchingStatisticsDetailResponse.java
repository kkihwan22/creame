package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class MatchingStatisticsDetailResponse {
    private String yearMonth;
    private Integer postTime;
    private Long postCoin;
    private Integer preTime;
    private Long preCoin;
    private Integer totalTime;
    private Long totalCoin;

    public MatchingStatisticsDetailResponse(String yearMonth, Integer postTime, Long postCoin, Integer preTime, Long preCoin, Integer totalTime, Long totalCoin) {
        this.yearMonth = yearMonth;
        this.postTime = postTime;
        this.postCoin = postCoin;
        this.preTime = preTime;
        this.preCoin = preCoin;
        this.totalTime = totalTime;
        this.totalCoin = totalCoin;
    }
}