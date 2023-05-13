package today.creame.web.matching.applicaton;

import java.util.List;

import com.querydsl.core.types.Order;
import today.creame.web.matching.applicaton.model.ReviewKindStatResult;
import today.creame.web.matching.applicaton.model.ReviewResult;

public interface ReviewQueryService {
    List<ReviewResult> getInfluenceReviews(Long influenceId, Order order);

    List<ReviewKindStatResult> getInfluenceReviewKindStat(Long influenceId);

    List<ReviewResult> getReviewsOfInfluence(Long influenceId);
}
