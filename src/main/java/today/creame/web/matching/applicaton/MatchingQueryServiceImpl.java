package today.creame.web.matching.applicaton;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.domain.InfluenceProfileImage;
import today.creame.web.influence.domain.InfluenceProfileImageJpaRepository;
import today.creame.web.matching.applicaton.model.*;
import today.creame.web.matching.domain.*;
import today.creame.web.matching.exception.NotFoundMatchingStatisticsException;

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
                .where(member.id.eq(memberId).and(matching.status.eq(MatchingProgressStatus.END)))
                .orderBy(matching.id.desc())
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
            throw new NotFoundMatchingStatisticsException();
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
                        matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.PRE, 0, 0L));
                    }else{
                        matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.POST, 0, 0L));
                    }
                }
            }
        }
        return matchingStatisticsResults;
    }

    private Map<Long, List<InfluenceProfileImage>> groupByProfileImage(Set<Long> influenceIdSet) {
        return influenceProfileImageJpaRepository.findByInfluence_IdIn(influenceIdSet).stream().collect(Collectors.groupingBy(it -> it.getInfluence().getId()));
    }
}
