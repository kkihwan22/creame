package today.creame.web.m2net.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceResult;
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

    @Override
    public void preCall(Long influenceId, Long memberId) {
        InfluenceResult counselor = influenceQuery.getInfluence(influenceId);
        MemberResult caller = memberQuery.getId(memberId);
        log.debug("find counselor: {}, caller: {}", counselor, caller);

        m2netClient.preCall(new M2netPrecallRequest(caller.getPhoneNumber(), counselor.getM2NetCounselorId()));
    }

    @Override
    public void postNotice() {

    }
}
