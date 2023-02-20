package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceGreetingsHistoryResult;
import today.creame.web.influence.application.model.InfluenceGreetingsQueryParameter;

public interface InfluenceGreetingHistoryQueryService {

    InfluenceGreetingsHistoryResult queryInfluenceId(InfluenceGreetingsQueryParameter influenceId);
}
