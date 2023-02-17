package today.creame.web.influence.application;

import static today.creame.web.influence.domain.QHotInfluence.hotInfluence;
import static today.creame.web.influence.domain.QInfluence.influence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.model.HotInfluenceResult;
import today.creame.web.influence.domain.HotInfluence;
import today.creame.web.influence.domain.HotInfluenceJpaRepository;
import today.creame.web.influence.domain.InfluenceCategoryJpaRepository;
import today.creame.web.influence.domain.QHotInfluence;
import today.creame.web.influence.domain.QInfluence;

@RequiredArgsConstructor
@Service
public class HotInfluenceQueryImpl implements HotInfluenceQuery {
    private final Logger log = LoggerFactory.getLogger(HotInfluenceQueryImpl.class);
    private final HotInfluenceJpaRepository hotInfluenceJpaRepository;
    private final JPAQueryFactory query;
    private final InfluenceCategoryJpaRepository influenceCategoryJpaRepository;
    private final QHotInfluence h = hotInfluence;
    private final QInfluence i = influence;

    @Override
    public List<HotInfluenceResult> getEnabledHotInfluenceList() {
        List<HotInfluence> hotInfluences = hotInfluenceJpaRepository.findHotInfluencesByEnabled(true);
        log.debug("hotInfluences: {}", hotInfluences);

        return hotInfluences.stream()
            .map(entity -> new HotInfluenceResult(entity))
            .collect(Collectors.toList());
    }
}
