package today.creame.web.m2net.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.m2net.application.model.M2netUpdateCallStatusCommand;
import today.creame.web.m2net.exception.NotEnoughBalanceException;
import today.creame.web.m2net.infra.feign.M2netClient;
import today.creame.web.m2net.infra.feign.io.M2netPrecallRequest;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.exception.NotFoundMemberException;
import today.creame.web.share.event.MatchingEvent;

@RequiredArgsConstructor
@Service
public class M2netServiceImpl implements M2netService {
    private final Logger log = LoggerFactory.getLogger(M2netServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final M2netClient m2netClient;
    private final ApplicationEventPublisher publisher;

    @Override
    public void preCall(Long influenceId, Long memberId) {
        Influence counselor = influenceJpaRepository.findById(influenceId)
                .orElseThrow(NotFoundInfluenceException::new);

        Member caller = memberJpaRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);
        log.debug("find counselor: {}, caller: {}", counselor, caller);

        if (caller.getCoins() < 10000) {
            log.info("Not enough balance. caller: {}, balance: {}", caller.getId(), caller.getCoins());
            throw new NotEnoughBalanceException();
        }

        m2netClient.preCall(new M2netPrecallRequest(caller.getPhoneNumber(), counselor.getM2NetCounselorId()));
    }

    @Override
    public void updateCallStatus(M2netUpdateCallStatusCommand command) {
        log.debug("[M2NET] CALLID : {}, telegram : {}", command.getCallId(), command.getTelegram());

        if (command.getReason().getMatchingProgressStatus() != null) {

            publisher.publishEvent(new MatchingEvent(
                    command.getMId(),
                    command.getCId(),
                    command.getCallId(),
                    command.getReason().getMatchingProgressStatus(),
                    command.getStartDateTime(),
                    command.getEndDateTime(),
                    command.isDeferred(),
                    command.getUsedAmount(),
                    command.getPaidType()));
        }
    }
}
