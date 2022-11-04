package today.creame.web.curation.application;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import today.creame.web.curation.domain.HotInfluence;
import today.creame.web.curation.domain.HotInfluenceJpaRepository;
import today.creame.web.home.application.HomeHotInfluenceQuery;
import today.creame.web.home.application.model.HomeHotInfluenceResult;

@RequiredArgsConstructor
@Component
public class HotInfluenceQuery implements HomeHotInfluenceQuery {
    private final Logger log = LoggerFactory.getLogger(HotInfluenceQuery.class);
    private final HotInfluenceJpaRepository hotInfluenceJpaRepository;

    @Override
    public List<HomeHotInfluenceResult> getHomeHotInfluence() {
        List<HotInfluence> results = hotInfluenceJpaRepository.findAll(Sort.by(Direction.ASC, "orderNumber"));
        log.debug("results: {}", results);
        return results.stream().map(result -> new HomeHotInfluenceResult(result)).collect(Collectors.toList());
    }
}