package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceGreetingApproveParameter;
import today.creame.web.influence.application.model.InfluenceGreetingCreateParameter;

public interface InfluenceGreetingsHistoryService {

    void create(InfluenceGreetingCreateParameter parameter);

    void approve(InfluenceGreetingApproveParameter parameter);
}
