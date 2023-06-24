package today.creame.web.matching.applicaton.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MatchingStatisticsParameter {
    private Long influenceId;
    private String toDate;
    private String fromDate;

    public MatchingStatisticsParameter(Long influenceId, String toDate, String fromDate) {
        this.influenceId = influenceId;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }
}
