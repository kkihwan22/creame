package today.creame.web.home.application.display;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.home.application.DisplayQuery;
import today.creame.web.home.entrypoint.io.HomeQueryParam;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingJapRepository;
import today.creame.web.matching.domain.MatchingProgressStatus;

@RequiredArgsConstructor
@Service
public class InfluenceQueryByCalling implements DisplayQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceQueryByCalling.class);
    private final MatchingJapRepository matchingJapRepository;
    private final InfluenceQuery influenceQuery;

    @Override
    public List<InfluenceResult> list(HomeQueryParam parameter) {
        List<Matching> results = matchingJapRepository.findMatchingsByStatus(MatchingProgressStatus.START);
        Set<Long> callingInfluenceSets = results.stream().map(result -> result.getInfluence()).map(influence -> influence.getId()).collect(Collectors.toSet());
        log.debug("calling influences: {}", callingInfluenceSets);
        return influenceQuery.listByCalling(callingInfluenceSets, parameter.getPageRequest());
    }
}