package today.creame.web.matching.applicaton;

import java.util.List;
import today.creame.web.matching.applicaton.model.MatchingHistoryResult;
import today.creame.web.matching.applicaton.model.MatchingResult;

public interface MatchingQueryService {

    List<MatchingHistoryResult> recentlyMyMatchingList();

    List<MatchingHistoryResult> history(Long memberId, int since);

    MatchingResult getMatching(Long matchingId);
}
