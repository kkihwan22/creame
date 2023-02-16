package today.creame.web.influence.application;

import static today.creame.web.influence.domain.QHotInfluence.hotInfluence;
import static today.creame.web.influence.domain.QInfluence.influence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.model.HotInfluenceResult;
import today.creame.web.influence.application.model.QHotInfluenceResult;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.influence.domain.InfluenceCategoryJpaRepository;
import today.creame.web.influence.domain.QHotInfluence;
import today.creame.web.influence.domain.QInfluence;

@RequiredArgsConstructor
@Service
public class HotInfluenceQueryImpl implements HotInfluenceQuery {
    private final Logger log = LoggerFactory.getLogger(HotInfluenceQueryImpl.class);
    private final JPAQueryFactory query;
    private final InfluenceCategoryJpaRepository influenceCategoryJpaRepository;
    private final QHotInfluence h = hotInfluence;
    private final QInfluence i = influence;

    @Override
    public List<HotInfluenceResult> getHotInfluences() {
        Map<Long, HotInfluenceResult> resultMap = query.select(new QHotInfluenceResult(h, i))
            .from(h, i)
            .where(h.influenceId.eq(i.id).and(h.enabled.eq(Boolean.TRUE)))
            .orderBy(h.createdBy.desc())
            .fetch().stream().collect(Collectors.toMap(HotInfluenceResult::getInfluenceId, Function.identity()));

        log.debug("resultMap: {}", resultMap);
        Set<Long> idSet = resultMap.keySet();
        log.debug("id sets: {}", idSet);
        List<InfluenceCategory> categories = influenceCategoryJpaRepository.findByIdIn(idSet);
        log.debug("categories: {}", categories);

        categories.forEach(it -> resultMap.get(it.getInfluenceId()).addCategory(it.getCategory()));

        return resultMap.values()
            .stream()
            .collect(Collectors.toList());
    }
}
