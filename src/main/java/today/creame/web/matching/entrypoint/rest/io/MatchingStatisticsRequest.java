package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import today.creame.web.matching.applicaton.model.MatchingStatisticsParameter;

import java.time.LocalDate;

@Getter
public class MatchingStatisticsRequest {
    Long influenceId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate targetDate;

    public MatchingStatisticsRequest(Long influenceId, LocalDate targetDate) {
        this.influenceId = influenceId;
        this.targetDate = targetDate;
    }

    public MatchingStatisticsParameter toParameter() {
        return new MatchingStatisticsParameter(this.influenceId, this.targetDate);
    }
}
