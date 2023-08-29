package today.creame.web.influence.application;

import java.util.List;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import today.creame.web.common.support.Utils;
import today.creame.web.influence.application.model.InfluenceGreetingSearchParameter;
import today.creame.web.influence.application.model.InfluenceGreetingsHistoryResult;
import today.creame.web.influence.application.model.InfluenceGreetingsQueryParameter;
import today.creame.web.influence.domain.GreetingsProgressStatus;
import today.creame.web.influence.domain.InfluenceGreetingsHistory;
import today.creame.web.influence.domain.InfluenceGreetingsHistoryJpaRepository;
import today.creame.web.share.aspect.permit.Permit;

import static today.creame.web.influence.domain.QInfluenceGreetingsHistory.influenceGreetingsHistory;

@RequiredArgsConstructor
@Service
public class InfluenceGreetingHistoryQueryServiceImpl implements InfluenceGreetingHistoryQueryService {
    private final Logger log = LoggerFactory.getLogger(InfluenceGreetingHistoryQueryServiceImpl.class);
    private final InfluenceGreetingsHistoryJpaRepository influenceGreetingsHistoryJpaRepository;
    private final JPAQueryFactory query;

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

    @Override
    public Page<InfluenceGreetingsHistory> list(InfluenceGreetingSearchParameter parameter, Pageable pageable) {
        QueryResults<InfluenceGreetingsHistory> result = query.selectFrom(influenceGreetingsHistory)
                .where(Utils.equalsOperation(influenceGreetingsHistory.status, parameter.getStatus()))
                .orderBy(influenceGreetingsHistory.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
