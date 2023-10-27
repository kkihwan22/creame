package today.creame.web.home.application.display;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.home.application.DisplayQuery;
import today.creame.web.home.entrypoint.io.HomeQueryParam;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.matching.domain.MatchingJapRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InfluenceQueryByCalling implements DisplayQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceQueryByCalling.class);
    private final InfluenceQuery influenceQuery;

    @Override
    public List<InfluenceResult> list(HomeQueryParam parameter) {
        return influenceQuery.listByCalling(parameter.getPageRequest());
    }
}