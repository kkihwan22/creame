package today.creame.web.matching.applicaton;

import today.creame.web.matching.applicaton.model.MatchingParameter;
import today.creame.web.matching.applicaton.model.MatchingStatisticsParameter;
import today.creame.web.matching.applicaton.model.MatchingStatisticsResult;

import java.util.List;

public interface MatchingService {
    void start(MatchingParameter parameter);

    void end(MatchingParameter parameter);

    List<MatchingStatisticsResult> getConsultationHoursPerMonth(MatchingStatisticsParameter parameter);

    // 리뷰
    // 답글
}
