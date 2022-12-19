package today.creame.web.matching.applicaton;

import java.util.List;
import today.creame.web.matching.applicaton.model.ReviewKindStatResult;
import today.creame.web.matching.applicaton.model.ReviewResult;

public interface ReviewQueryService {
    List<ReviewResult> listReviewByInfluence(Long influenceId, String sort);

    List<ReviewKindStatResult> getReviewKindsStatByInfluence(Long influenceId);
}
