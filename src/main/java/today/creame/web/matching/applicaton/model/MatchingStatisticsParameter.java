package today.creame.web.matching.applicaton.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MatchingStatisticsParameter {
    private Long influenceId;
    private Integer since;

    public MatchingStatisticsParameter(Long influenceId, Integer since) {
        this.influenceId = influenceId;
        this.since = since;
    }
}
