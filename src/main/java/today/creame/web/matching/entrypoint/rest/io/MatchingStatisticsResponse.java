package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;


@Getter
@NoArgsConstructor
public class MatchingStatisticsResponse {
    private String yearMonth;
    private MatchingStatisticsDetail detail;


    public MatchingStatisticsResponse(String yearMonth, MatchingStatisticsDetail detail) {
        this.yearMonth = yearMonth;
        this.detail = detail;
    }

    @Getter
    @NoArgsConstructor
    public static class MatchingStatisticsDetail {
        private Long totalCoin;
        private LocalTime postTime;
        private LocalTime preTime;

        public MatchingStatisticsDetail(Long totalCoin, LocalTime postTime, LocalTime preTime) {
            this.totalCoin = totalCoin;
            this.postTime = postTime;
            this.preTime = preTime;
        }
    }
}
