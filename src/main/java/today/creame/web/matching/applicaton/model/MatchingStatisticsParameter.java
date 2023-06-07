package today.creame.web.matching.applicaton.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MatchingStatisticsParameter {
    Long influenceId;
    LocalDate targetDate;

    public MatchingStatisticsParameter(Long influenceId, LocalDate targetDate) {
        this.influenceId = influenceId;
        this.targetDate = targetDate;
    }
}
