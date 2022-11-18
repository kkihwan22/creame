package today.creame.web.influence.application;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.model.HotInfluenceResult;
import today.creame.web.influence.domain.HotInfluence;
import today.creame.web.influence.domain.HotInfluenceJpaRepository;

@RequiredArgsConstructor
@Service
public class HotInfluenceQueryImpl implements HotInfluenceQuery {
    private final Logger log = LoggerFactory.getLogger(HotInfluenceQueryImpl.class);
    private final HotInfluenceJpaRepository hotInfluenceJpaRepository;

    @Override
    public List<HotInfluenceResult> getHotInfluenceResults() {
        List<HotInfluence> results = hotInfluenceJpaRepository.findAll();
        return results.stream()
            .map(result -> new HotInfluenceResult(result))
            .collect(Collectors.toList());
    }
}
