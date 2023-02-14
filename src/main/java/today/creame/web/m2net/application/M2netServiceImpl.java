package today.creame.web.m2net.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.m2net.application.model.M2netNoticeCommand;
import today.creame.web.m2net.domain.M2netNoticeHistory;
import today.creame.web.m2net.domain.M2netRecepitHistroyJpaRepository;
import today.creame.web.m2net.infra.feign.M2netClient;
import today.creame.web.m2net.infra.feign.io.M2netPrecallRequest;
import today.creame.web.member.application.MemberQuery;
import today.creame.web.member.application.model.MemberResult;

@RequiredArgsConstructor
@Service
public class M2netServiceImpl implements M2netService {
    private final Logger log = LoggerFactory.getLogger(M2netServiceImpl.class);
    private final MemberQuery memberQuery;
    private final InfluenceQuery influenceQuery;
    private final M2netClient m2netClient;
    private final M2netRecepitHistroyJpaRepository m2netRecepitHistroyJpaRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public void preCall(Long influenceId, Long memberId) {
        InfluenceResult counselor = influenceQuery.getInfluence(influenceId);
        MemberResult caller = memberQuery.getId(memberId);
        log.debug("find counselor: {}, caller: {}", counselor, caller);

        m2netClient.preCall(new M2netPrecallRequest(caller.getPhoneNumber(), counselor.getM2NetCounselorId()));
    }

    @Transactional
    @Override
    public void postNotice(M2netNoticeCommand command) {
        log.debug("[M2NET] command: {}", command);
        M2netNoticeHistory entity = m2netRecepitHistroyJpaRepository.save(command.toEntity());

        m2netRecepitHistroyJpaRepository.save(entity);
    }
}
