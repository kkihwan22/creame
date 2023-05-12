package today.creame.web.matching.applicaton;

import java.util.List;
import today.creame.web.matching.applicaton.model.MatchingHistoryResult;
import today.creame.web.matching.applicaton.model.MatchingResult;
import today.creame.web.matching.applicaton.model.MyReviewResult;

public interface MatchingQueryService {

    List<MatchingHistoryResult> recentlyMyMatchingList();

    List<MatchingHistoryResult> listMatching(Long memberId, int since);

    MatchingResult getMatching(Long matchingId);

    List<MyReviewResult> listMyReview(Long memberId);
}
