package today.creame.web.matching.applicaton;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import today.creame.web.common.support.Utils;
import today.creame.web.influence.domain.InfluenceProfileImage;
import today.creame.web.influence.domain.InfluenceProfileImageJpaRepository;
import today.creame.web.matching.applicaton.model.*;
import today.creame.web.matching.domain.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static today.creame.web.influence.domain.QInfluence.influence;
import static today.creame.web.influence.domain.QInfluenceProfileImage.influenceProfileImage;
import static today.creame.web.matching.domain.QMatching.matching;
import static today.creame.web.matching.domain.QMatchingReview.matchingReview;
import static today.creame.web.member.domain.QMember.member;

@RequiredArgsConstructor
@Service
public class MatchingQueryServiceImpl implements MatchingQueryService {
    private final Logger log = LoggerFactory.getLogger(MatchingQueryServiceImpl.class);
    private final InfluenceProfileImageJpaRepository influenceProfileImageJpaRepository;
    private final MatchingJapRepository matchingJapRepository;
    private final ReviewLikedJpaRepository reviewLikedJpaRepository;
    private final JPAQueryFactory query;

    private static final Map<String, Expression<?>> MATCHING_PROPERTY_MAP = new HashMap<>();
    static {
        MATCHING_PROPERTY_MAP.put("id", matching.id);
        MATCHING_PROPERTY_MAP.put("createdDateTime", matching.createdDateTime);
        MATCHING_PROPERTY_MAP.put("updatedDateTime", matching.updatedDateTime);
    }

    public OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            Sort.Order order = pageable.getSort().iterator().next();
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Expression<?> property = MATCHING_PROPERTY_MAP.get(order.getProperty());
            if (property != null) {
                return new OrderSpecifier(direction, property);
            }
        }
        return null;
    }
    @Override
    public List<MatchingHistoryResult> listMatching(Long memberId, int since) {
        LocalDateTime datetime = LocalDate.now().minusMonths(since).atTime(LocalTime.MIDNIGHT);
        List<Matching> matchings = query.selectFrom(matching)
                .join(matching.member, member).fetchJoin()
                .join(matching.influence, influence).fetchJoin()
                .where(member.id.eq(memberId).and(matching.createdDateTime.after(datetime)))
                .orderBy(matching.id.desc())
                .fetch();
        log.debug("matchings: {}", matchings);

        Set<Long> collect = matchings.stream().map(it -> it.getInfluence().getId()).collect(Collectors.toSet());
        Map<Long, List<InfluenceProfileImage>> groupBy = groupByProfileImage(collect);

        return matchings.stream()
                .map(matching -> new MatchingHistoryResult(matching, groupBy.get(matching.getInfluence().getId()).get(0).getFileResourceUri()))
                .collect(Collectors.toList());
    }

    @Override
    public MatchingResult getMatching(Long matchingId) {
        Matching match = query.selectFrom(matching)
                .join(matching.member, member).fetchJoin()
                .join(matching.influence, influence).fetchJoin()
                .where(matching.id.eq(matchingId))
                .fetchOne();
        return new MatchingResult(match);
    }

    @Override
    public List<MyReviewResult> listMyReview(Long memberId) {
        List<Matching> matchings = query.selectFrom(matching)
                .join(matching.member, member).fetchJoin()
                .join(matching.influence, influence).fetchJoin()
                .leftJoin(matching.matchingReviews, matchingReview)
                .leftJoin(influence.profileImages, influenceProfileImage)
                .where(member.id.eq(memberId)
                        .and(matching.status.eq(MatchingProgressStatus.END))
                        .and(matching.reviewable.eq(Boolean.TRUE))
                        .and(matching.endedDateTime.before(LocalDate.now().minusDays(5L).atTime(LocalTime.MAX)))
                )
                .orderBy(matching.id.desc())
                .distinct()
                .fetch();
        log.debug("matchings: {}", matchings);
        Set<Long> reviewIdSet = matchings.stream()
                .filter(m -> !m.getMatchingReviews().isEmpty())
                .map(m -> m.getMatchingReviews())
                .map(list -> list.get(0))
                .map(r -> r.getId())
                .collect(Collectors.toSet());

        Map<Long, Boolean> mapReviewLiked = reviewLikedJpaRepository.findReviewLikesByReviewIdInAndMemberId(reviewIdSet, memberId)
                .stream()
                .collect(Collectors.toMap(ReviewLiked::getReviewId, ReviewLiked::isLiked));

        log.debug("mapReviewLiked: {}", mapReviewLiked);

        return matchings.stream()
                .map(matching -> new MyReviewResult(matching, mapReviewLiked)).collect(Collectors.toList());
    }

    @Override
    public List<MatchingStatisticsResult> getConsultationHoursPerMonth(MatchingStatisticsParameter parameter) {
        LocalDate toDate = LocalDate.parse(parameter.getToDate() + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate fromDate = LocalDate.parse(parameter.getFromDate() + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        Map<String, List<MatchingStatisticsResult>> map = new HashMap<>();

        List<Object[]> objects = matchingJapRepository.getConsultationHoursPerMonth(parameter.getInfluenceId(), parameter.getFromDate(), parameter.getToDate());
        List<MatchingStatisticsResult> matchingStatisticsResults = CollectionUtils.emptyIfNull(objects).stream().map(MatchingStatisticsResult::new).collect(Collectors.toList());
        if(Collections.isEmpty(matchingStatisticsResults)) {
            return matchingStatisticsResults;
            // throw new NotFoundMatchingStatisticsException();
        }

        for(MatchingStatisticsResult target : matchingStatisticsResults) {
            List<MatchingStatisticsResult> list = map.getOrDefault(target.getYearMonth(), new ArrayList<>());
            list.add(target);
            map.put(target.getYearMonth(), list);
        }

        long num = toDate.until(fromDate, ChronoUnit.MONTHS);

        for(long i = 0; i <= num; i++){
            LocalDate date = toDate.plusMonths(i);
            String dateString = date.format(DateTimeFormatter.ofPattern("yyyyMM"));

            List<MatchingStatisticsResult> list = map.get(dateString);

            if(list == null){
                matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.POST, 0, 0L));
                matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.PRE, 0, 0L));
            }else {
                if(list.size() == 1){
                    if(list.get(0).getPaidType() == PaidType.POST){
                        matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.POST, 0, 0L));
                    }else{
                        matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.PRE, 0, 0L));
                    }
                }
            }
        }
        return matchingStatisticsResults;
    }

    @Override
    public Page<Matching> list(MatchingSearchParameter parameter, Pageable pageable) {
        DateExpression standardDate = Utils.dateFormat(matching.startDateTime, "%Y-%m-%d");

        QueryResults<Matching> result =  query.selectFrom(matching)
                .where(eqIdOrNickname(parameter),
                        Utils.dateBetween(standardDate, parameter.getStartDt(), parameter.getEndDt()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());

    }

    private Map<Long, List<InfluenceProfileImage>> groupByProfileImage(Set<Long> influenceIdSet) {
        return influenceProfileImageJpaRepository.findByInfluence_IdIn(influenceIdSet).stream().collect(Collectors.groupingBy(it -> it.getInfluence().getId()));
    }

    private BooleanExpression eqIdOrNickname(MatchingSearchParameter parameter) {
        Long id = parameter.getId();
        String nickname = parameter.getNickname();

        switch (parameter.getType().toUpperCase()) {
            case "MEMBER" :
                if(Objects.nonNull(id)) {
                    return matching.member.id.eq(id);

                } else if(!StringUtils.isEmpty(nickname)) {
                    return matching.member.nickname.eq(nickname);
                }
                break;

            case "INFLUENCE" :
                if(Objects.nonNull(id)) {
                    return matching.influence.id.eq(id);

                } else if(!StringUtils.isEmpty(nickname)) {
                    return matching.influence.nickname.eq(nickname);
                }
                break;
        }
        return null;
    }
}
