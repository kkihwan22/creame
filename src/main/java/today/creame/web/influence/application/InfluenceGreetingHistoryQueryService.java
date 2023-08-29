package today.creame.web.influence.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import today.creame.web.influence.application.model.InfluenceGreetingSearchParameter;
import today.creame.web.influence.application.model.InfluenceGreetingsHistoryResult;
import today.creame.web.influence.application.model.InfluenceGreetingsQueryParameter;
import today.creame.web.influence.domain.InfluenceGreetingsHistory;

public interface InfluenceGreetingHistoryQueryService {

    InfluenceGreetingsHistoryResult queryInfluenceId(InfluenceGreetingsQueryParameter influenceId);
    Page<InfluenceGreetingsHistory> list(InfluenceGreetingSearchParameter parameter, Pageable pageable);
}
