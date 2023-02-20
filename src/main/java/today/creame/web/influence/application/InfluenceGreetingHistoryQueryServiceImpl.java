package today.creame.web.influence.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.model.InfluenceGreetingsHistoryResult;
import today.creame.web.influence.application.model.InfluenceGreetingsQueryParameter;
import today.creame.web.influence.domain.GreetingsProgressStatus;
import today.creame.web.influence.domain.InfluenceGreetingsHistory;
import today.creame.web.influence.domain.InfluenceGreetingsHistoryJpaRepository;
import today.creame.web.share.aspect.permit.Permit;

@RequiredArgsConstructor
@Service
public class InfluenceGreetingHistoryQueryServiceImpl implements InfluenceGreetingHistoryQueryService {
    private final Logger log = LoggerFactory.getLogger(InfluenceGreetingHistoryQueryServiceImpl.class);
    private final InfluenceGreetingsHistoryJpaRepository influenceGreetingsHistoryJpaRepository;

    @Override
    @Permit
    public InfluenceGreetingsHistoryResult queryInfluenceId(InfluenceGreetingsQueryParameter parameter) {
        List<InfluenceGreetingsHistory> results
            = influenceGreetingsHistoryJpaRepository.findByInfluence_IdAndStatusOrderByUpdatedByDesc(parameter.getInfluenceId(), GreetingsProgressStatus.REQUEST);
        log.debug("results: {}", results);
        return results.isEmpty()
            ? null
            : new InfluenceGreetingsHistoryResult(results.get(0));
    }
}
