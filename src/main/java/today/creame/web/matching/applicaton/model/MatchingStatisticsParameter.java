package today.creame.web.matching.applicaton.model;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class MatchingStatisticsParameter {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

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

    public MatchingStatisticsParameter(Long influenceId, LocalDate date) {
        this(influenceId, date.format(formatter), date.format(formatter));
    }

    public static MatchingStatisticsParameter fromLastMonthBefore(Long influenceId, int before) {
        LocalDate last = LocalDate.now().minusMonths(1);
        LocalDate to = last.minusMonths(before);
        return new MatchingStatisticsParameter(influenceId, to.format(formatter), last.format(formatter));
    }
}
