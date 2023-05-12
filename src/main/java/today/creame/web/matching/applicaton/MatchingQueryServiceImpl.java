package today.creame.web.matching.applicaton;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.domain.InfluenceProfileImage;
import today.creame.web.influence.domain.InfluenceProfileImageJpaRepository;
import today.creame.web.matching.applicaton.model.MatchingHistoryResult;
import today.creame.web.matching.applicaton.model.MatchingResult;
import today.creame.web.matching.applicaton.model.MyReviewResult;
import today.creame.web.matching.domain.Matching;
import today.creame.web.share.support.SecurityContextSupporter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static today.creame.web.influence.domain.QInfluence.influence;
import static today.creame.web.matching.domain.QMatching.matching;
import static today.creame.web.matching.domain.QMatchingReview.matchingReview;
import static today.creame.web.member.domain.QMember.member;

@RequiredArgsConstructor
@Service
public class MatchingQueryServiceImpl implements MatchingQueryService {
    private final Logger log = LoggerFactory.getLogger(MatchingQueryServiceImpl.class);
    private final InfluenceProfileImageJpaRepository influenceProfileImageJpaRepository;
    private final JPAQueryFactory query;
    @Override
    public List<MatchingHistoryResult> recentlyMyMatchingList() {
        Long me = SecurityContextSupporter.getId();
        return this.listMatching(me, 1);
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
                .where(member.id.eq(memberId))
                .orderBy(matching.id.desc())
                .fetch();
        log.debug("matchings: {}", matchings);

        Set<Long> collect = matchings.stream().map(it -> it.getInfluence().getId()).collect(Collectors.toSet());
        Map<Long, List<InfluenceProfileImage>> groupBy = groupByProfileImage(collect);

        return matchings.stream().map(matching -> new MyReviewResult(matching, groupBy.get(matching.getInfluence().getId()).get(0).getFileResourceUri())).collect(Collectors.toList());
    }

    private Map<Long, List<InfluenceProfileImage>> groupByProfileImage(Set<Long> collect) {
        return influenceProfileImageJpaRepository.findByInfluence_IdIn(collect).stream().collect(Collectors.groupingBy(it -> it.getInfluence().getId()));
    }


}
