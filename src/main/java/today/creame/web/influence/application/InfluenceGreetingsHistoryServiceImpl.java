package today.creame.web.influence.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceGreetingApproveParameter;
import today.creame.web.influence.application.model.InfluenceGreetingCreateParameter;
import today.creame.web.influence.application.model.InfluenceGreetingRejectParameter;
import today.creame.web.influence.domain.*;
import today.creame.web.influence.exception.NotFoundGreetingsHistoryException;
import today.creame.web.share.aspect.permit.Permit;

@RequiredArgsConstructor
@Service
public class InfluenceGreetingsHistoryServiceImpl implements InfluenceGreetingsHistoryService {
    private final Logger log = LoggerFactory.getLogger(InfluenceGreetingsHistoryServiceImpl.class);
    private final InfluenceGreetingsHistoryJpaRepository influenceGreetingsHistoryJpaRepository;

    @Transactional
    @Permit
    @Override
    public void create(InfluenceGreetingCreateParameter parameter) {
        List<InfluenceGreetingsHistory> results = influenceGreetingsHistoryJpaRepository
            .findByInfluence_IdAndStatusOrderByUpdatedByDesc(parameter.getInfluenceId(), GreetingsProgressStatus.REQUEST);

        if (!results.isEmpty()) {
            results.get(0).reRequest(parameter.getFileId(), parameter.getFileUri());
        } else {
            influenceGreetingsHistoryJpaRepository.save(parameter.toEntity());
        }
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

    @Transactional
    @Override
    public void reject(InfluenceGreetingRejectParameter parameter) {
        InfluenceGreetingsHistory influenceGreetingsHistory = influenceGreetingsHistoryJpaRepository
                .findById(parameter.getRequestId())
                .orElseThrow(NotFoundGreetingsHistoryException::new);

        influenceGreetingsHistory.reject();
    }
}