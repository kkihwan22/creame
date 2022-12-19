package today.creame.web.matching.applicaton;

import java.util.List;
import today.creame.web.matching.applicaton.model.MatchingResult;

public interface MatchingQueryService {

    List<MatchingResult> recentlyMyMatching(Long memberId, boolean latest);
}
