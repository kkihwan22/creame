package today.creame.web.matching.applicaton;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.querydsl.core.types.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import today.creame.web.matching.applicaton.model.ReviewKindStatResult;
import today.creame.web.matching.applicaton.model.ReviewResult;
import today.creame.web.matching.domain.MatchingReview;

public interface ReviewQueryService {
    List<ReviewResult> getInfluenceReviews(Long influenceId, Order order);

    List<ReviewKindStatResult> getInfluenceReviewKindStat(Long influenceId);

    List<ReviewResult> getReviewsByInfluence(Long influenceId);

    Map<Long, List<ReviewResult>> getReviewGroupByInfluences(Set<Long> idSet);
    Page<MatchingReview> list(Long matchingId, Pageable pageable);

}
