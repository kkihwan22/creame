package today.creame.web.matching.applicaton;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import today.creame.web.matching.applicaton.model.ReviewKindStatResult;
import today.creame.web.matching.applicaton.model.ReviewResult;
import today.creame.web.matching.domain.*;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.QMember;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static today.creame.web.matching.domain.QMatching.matching;
import static today.creame.web.matching.domain.QMatchingReview.matchingReview;
import static today.creame.web.member.domain.QMember.member;

@RequiredArgsConstructor
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final Logger log = LoggerFactory.getLogger(ReviewQueryServiceImpl.class);
    private final JPAQueryFactory query;
    private final ReviewKindsStatJpaRepository reviewKindsStatJpaRepository;


    @Override
    public List<ReviewResult> getInfluenceReviews(Long influenceId, Order order) {
        List<ReviewResult> results = query.select(Projections.constructor(ReviewResult.class,
                        matchingReview,
                        matching,
                        member))
                .from(matchingReview)
                .join(matchingReview.matching, matching).fetchJoin()
                .join(matching.member, member).fetchJoin()
                .where(matching.influence.id.eq(influenceId))
                .orderBy(new OrderSpecifier<>(Order.DESC, matchingReview.id))
                .fetch();
        log.debug("results:{}", results);
        return results;
    }

    @Override
    public List<ReviewKindStatResult> getInfluenceReviewKindStat(Long influenceId) {
        List<ReviewKindsStat> results = reviewKindsStatJpaRepository.findReviewKindsStatByInfluenceId(influenceId);
        log.debug("results: {}", results);
        return results.stream()
            .map(result -> new ReviewKindStatResult(result.getKinds(), result.getTotal()))
            .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResult> getReviewsOfInfluence(Long influenceId) {
        return this.getInfluenceReviews(influenceId, Order.DESC);
    }
}
