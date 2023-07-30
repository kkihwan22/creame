package today.creame.web.matching.applicaton;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.matching.applicaton.model.MatchingParameter;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingJapRepository;
import today.creame.web.matching.exception.NotFoundMatchingException;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.exception.NotFoundMemberException;
import today.creame.web.share.event.MatchingEndEvent;

@RequiredArgsConstructor
@Service
public class MatchingServiceImpl implements MatchingService {
    private final Logger log = LoggerFactory.getLogger(MatchingServiceImpl.class);
    private final MemberJpaRepository memberJpaRepository;
    private final InfluenceJpaRepository influenceJpaRepository;
    private final MatchingJapRepository matchingJapRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    @Override
    public void start(MatchingParameter parameter) {
        Member member = findMember(parameter.getUid());
        Influence influence = findInfluence(parameter.getCid());

        Matching matching = new Matching(
            influence,
            member,
            parameter.getCallId(),
            parameter.getMatchingProgressStatus(),
            parameter.getStartDateTime(),
            parameter.getEndDateTime(),
            parameter.isDeferred(),
            parameter.getUsedCoins(), parameter.getPaidType()
        );

        matchingJapRepository.save(matching);
        influence.updateCalling();
    }

    @Transactional
    @Override
    public void end(MatchingParameter parameter) {
        Matching matching = matchingJapRepository.findMatchingByCallId(parameter.getCallId()).orElseThrow(NotFoundMatchingException::new);
        matching.end(parameter.getMatchingProgressStatus(), parameter.getEndDateTime(), parameter.getUsedCoins());
        matching.getInfluence().updateCalling();
        log.debug("matching: {}", matching);

        // matchig end event 같은게 필요함!
        publisher.publishEvent(new MatchingEndEvent(matching.getMember().getId(), matching.getUsedCoins()));
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
