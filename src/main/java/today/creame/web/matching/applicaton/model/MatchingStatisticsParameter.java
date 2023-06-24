package today.creame.web.matching.applicaton.model;

import lombok.Getter;

@Getter
public class MatchingStatisticsParameter {
    private Long influenceId;
    private String toDate;
    private String fromDate;

    public MatchingStatisticsParameter(Long influenceId, String fromDate, String toDate) {
        this.influenceId = influenceId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public MatchingStatisticsParameter(Long influenceId, String date) {
        this(influenceId, date, date);
    }
}
