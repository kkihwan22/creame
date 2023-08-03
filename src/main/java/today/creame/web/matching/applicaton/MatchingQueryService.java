package today.creame.web.matching.applicaton;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import today.creame.web.matching.applicaton.model.*;
import today.creame.web.matching.domain.Matching;

public interface MatchingQueryService {
    List<MatchingHistoryResult> listMatching(Long memberId, int since);

    MatchingResult getMatching(Long matchingId);

    List<MyReviewResult> listMyReview(Long memberId);

    List<MatchingStatisticsResult> getConsultationHoursPerMonth(MatchingStatisticsParameter parameter);

    Page<Matching> list(MatchingSearchParameter parameter, Pageable pageable);
}
