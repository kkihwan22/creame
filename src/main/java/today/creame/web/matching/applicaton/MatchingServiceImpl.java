package today.creame.web.matching.applicaton;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.matching.applicaton.model.MatchingParameter;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingJapRepository;
import today.creame.web.matching.domain.MatchingProgressStatus;
import today.creame.web.matching.exception.NotFoundMatchingException;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.exception.NotFoundMemberException;

import java.util.List;
import java.util.Set;

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
