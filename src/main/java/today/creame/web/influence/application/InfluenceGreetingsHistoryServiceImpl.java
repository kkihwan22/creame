package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceGreetingApproveParameter;
import today.creame.web.influence.application.model.InfluenceGreetingCreateParameter;
import today.creame.web.influence.domain.InfluenceGreetingsHistory;
import today.creame.web.influence.domain.InfluenceGreetingsHistoryJpaRepository;
import today.creame.web.influence.exception.NotFoundGreetingsHistoryException;
import today.creame.web.share.aspect.permit.Permit;

@RequiredArgsConstructor
@Service
public class InfluenceGreetingsHistoryServiceImpl implements InfluenceGreetingsHistoryService {
    private final Logger log = LoggerFactory.getLogger(InfluenceGreetingsHistoryServiceImpl.class);
    private final InfluenceGreetingsHistoryJpaRepository influenceGreetingsHistoryJpaRepository;

    @Override
    @Permit
    public void create(InfluenceGreetingCreateParameter parameter) {
        influenceGreetingsHistoryJpaRepository.save(parameter.toEntity());
    }

    @Transactional
    @Override
    public void approve(InfluenceGreetingApproveParameter parameter) {
        InfluenceGreetingsHistory influenceGreetingsHistory = influenceGreetingsHistoryJpaRepository
            .findById(parameter.getRequestId())
            .orElseThrow(NotFoundGreetingsHistoryException::new);
        log.debug("find influence greeting history: {}", influenceGreetingsHistory);

        influenceGreetingsHistory.approve();
    }
}