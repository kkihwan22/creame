package today.creame.web.matching.applicaton;

import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.matching.applicaton.model.MatchingParameter;
import today.creame.web.matching.applicaton.model.MatchingStatisticsParameter;
import today.creame.web.matching.applicaton.model.MatchingStatisticsResult;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingJapRepository;
import today.creame.web.matching.domain.PaidType;
import today.creame.web.matching.exception.NotFoundMatchingException;
import today.creame.web.matching.exception.NotFoundMatchingStatisticsException;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.exception.NotFoundMemberException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import static today.creame.web.matching.domain.MatchingProgressStatus.INSUFFICIENT;
import static today.creame.web.matching.domain.MatchingProgressStatus.START;

@RequiredArgsConstructor
@Service
public class MatchingServiceImpl implements MatchingService {
    private final Logger log = LoggerFactory.getLogger(MatchingServiceImpl.class);
    private final MemberJpaRepository memberJpaRepository;
    private final InfluenceJpaRepository influenceJpaRepository;
    private final MatchingJapRepository matchingJapRepository;

    @Transactional
    @Override
    public void start(MatchingParameter parameter) {
        Member member = findMember(parameter.getUid());
        Influence influence = findInfluence(parameter.getCid());

        Matching matching = new Matching(
            influence,
            member,
            parameter.getMatchingProgressStatus(),
            parameter.getStartDateTime(),
            parameter.getEndDateTime(),
            parameter.isDeferred(),
            parameter.getUsedCoins(), parameter.getPaidType()
        );

        matchingJapRepository.save(matching);
    }

    @Transactional
    @Override
    public void end(MatchingParameter parameter) {
        Influence influence = findInfluence(parameter.getCid());
        Member member = findMember(parameter.getUid());

        List<Matching> matchings = matchingJapRepository.findMatchingsByInfluenceAndMemberAndStatusInAndStartDateTimeBetween(
                influence,
                member,
                Set.of(START, INSUFFICIENT),
                parameter.getStartDateTime().minusMinutes(1),
                parameter.getStartDateTime().plusMinutes(1));
        log.debug("Matchings: {}", matchings);

        if (matchings.isEmpty()) throw new NotFoundMatchingException();

        Matching matching = matchings.get(matchings.size() - 1);
        matching.end(parameter.getMatchingProgressStatus(), parameter.getEndDateTime(), parameter.getUsedCoins());
        log.debug("matching: {}", matching);
    }

    @Override
    public List<MatchingStatisticsResult> getConsultationHoursPerMonth(MatchingStatisticsParameter parameter) {
        LocalDate toDate = LocalDate.parse(parameter.getToDate() + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate fromDate = LocalDate.parse(parameter.getFromDate() + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        Map<String, List<MatchingStatisticsResult>> map = new HashMap<>();

        List<Object[]> objects = matchingJapRepository.getConsultationHoursPerMonth(parameter.getInfluenceId(), parameter.getToDate(), parameter.getFromDate());
        List<MatchingStatisticsResult> matchingStatisticsResults = CollectionUtils.emptyIfNull(objects).stream().map(MatchingStatisticsResult::new).collect(Collectors.toList());
        if(Collections.isEmpty(matchingStatisticsResults)) {
            throw new NotFoundMatchingStatisticsException();
        }

        for(MatchingStatisticsResult target : matchingStatisticsResults) {
            List<MatchingStatisticsResult> list = map.getOrDefault(target.getYearMonth(), new ArrayList<MatchingStatisticsResult>());
            list.add(target);
            map.put(target.getYearMonth(), list);
        }

        long num = toDate.until(fromDate, ChronoUnit.MONTHS);

        for(long i = 0; i <= num; i++){
            LocalDate date = toDate.plusMonths(i);
            String dateString = date.format(DateTimeFormatter.ofPattern("yyyyMM"));

            List<MatchingStatisticsResult> list = map.get(dateString);

            if(list == null){
                matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.POST, LocalTime.of(0, 0, 0), 0L));
                matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.PRE, LocalTime.of(0, 0, 0), 0L));
            }else {
                if(list.size() == 1){
                    if(list.get(0).getPaidType() == PaidType.POST){
                        matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.PRE, LocalTime.of(0, 0, 0), 0L));
                    }else{
                        matchingStatisticsResults.add(new MatchingStatisticsResult(dateString, PaidType.POST, LocalTime.of(0, 0, 0), 0L));
                    }
                }
            }
        }
        return matchingStatisticsResults;
    }

    private Influence findInfluence(String cid) {
        Influence influence = influenceJpaRepository
            .findInfluenceByM2NetCounselorId(cid)
            .orElseThrow(() -> new NotFoundInfluenceException());

        log.debug("find influence: {}", influence);
        return influence;
    }

    private Member findMember(String uid) {
        Member member = memberJpaRepository
            .findMemberByM2netUserId(uid)
            .orElseThrow(() -> new NotFoundMemberException());

        log.debug("find member: {}", member);
        return member;
    }
}
