package today.creame.web.matching.applicaton;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import today.creame.web.common.support.Utils;
import today.creame.web.matching.applicaton.model.ReviewKindStatResult;
import today.creame.web.matching.applicaton.model.ReviewResult;
import today.creame.web.matching.applicaton.model.ReviewSearchParameter;
import today.creame.web.matching.domain.*;
import today.creame.web.share.support.SecurityContextSupporter;

import java.util.*;
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
    private final ReviewLikedJpaRepository reviewLikedJpaRepository;

    private static final Map<String, Expression<?>> REVIEW_PROPERTY_MAP = new HashMap<>();
    static {
        REVIEW_PROPERTY_MAP.put("id", matchingReview.id);
        REVIEW_PROPERTY_MAP.put("createdDateTime", matchingReview.createdDateTime);
        REVIEW_PROPERTY_MAP.put("updatedDateTime", matchingReview.updatedDateTime);
    }

    public OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            Sort.Order order = pageable.getSort().iterator().next();
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Expression<?> property = REVIEW_PROPERTY_MAP.get(order.getProperty());
            if (property != null) {
                return new OrderSpecifier(direction, property);
            }
        }
        return null;
    }

    @Override
    public List<ReviewResult> getInfluenceReviews(Long influenceId, Order order) {
        List<ReviewResult> results = query.select(Projections.constructor(ReviewResult.class, matchingReview))
                .from(matchingReview)
                .join(matchingReview.matching, matching).fetchJoin()
                .join(matching.member, member).fetchJoin()
                .where(matching.influence.id.eq(influenceId))
                .orderBy(new OrderSpecifier<>(order, matchingReview.id))
                .fetch();
        log.debug("results:{}", results);

        Set<Long> reviewIdSet = results.stream().map(reviewResult -> reviewResult.getReviewId()).collect(Collectors.toSet());
        Map<Long, Boolean> mapReviewLikeResult = reviewLikedJpaRepository
                .findReviewLikesByReviewIdInAndMemberId(reviewIdSet, SecurityContextSupporter.orElseGetEmpty())
                .stream()
                .collect(Collectors.toMap(ReviewLiked::getReviewId, ReviewLiked::isLiked));

        results.stream().forEach(result -> result.withLiked(mapReviewLikeResult.getOrDefault(result.getReviewId(), false)));
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
    public List<ReviewResult> getReviewsByInfluence(Long influenceId) {
        return this.getInfluenceReviews(influenceId, Order.DESC);
    }

    @Override
    public Map<Long, List<ReviewResult>> getReviewGroupByInfluences(Set<Long> idSet) {
        List<ReviewResult> results = query.select(Projections.constructor(ReviewResult.class, matchingReview))
                .from(matchingReview)
                .join(matchingReview.matching, matching).fetchJoin()
                .join(matching.member, member).fetchJoin()
                .where(matching.influence.id.in(idSet))
                .orderBy(new OrderSpecifier<>(Order.DESC, matchingReview.id))
                .fetch();

        return results.stream().collect(Collectors.groupingBy(ReviewResult::getInfluenceId));
    }

    @Override
    public Page<MatchingReview> listByMatchingId(Long matchingId, Pageable pageable) {
        QueryResults<MatchingReview> result =  query.selectFrom(matchingReview)
                .where(matchingReview.matching.id.eq(matchingId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());

    }

    @Override
    public Page<MatchingReview> list(ReviewSearchParameter parameter, Pageable pageable) {
        QueryResults<MatchingReview> result =  query.selectFrom(matchingReview)
                .where(Utils.equalsOperation(matchingReview.matching.member.id, parameter.getMemberId()),
                       Utils.equalsOperation(matchingReview.matching.member.nickname, parameter.getMemberNickname()),
                       Utils.equalsOperation(matchingReview.matching.influence.id, parameter.getInfluenceId()),
                       Utils.equalsOperation(matchingReview.matching.influence.nickname, parameter.getInfluenceNickname()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
