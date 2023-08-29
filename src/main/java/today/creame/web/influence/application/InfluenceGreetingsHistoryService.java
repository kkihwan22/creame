package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceGreetingApproveParameter;
import today.creame.web.influence.application.model.InfluenceGreetingCreateParameter;
import today.creame.web.influence.application.model.InfluenceGreetingRejectParameter;

public interface InfluenceGreetingsHistoryService {

    void create(InfluenceGreetingCreateParameter parameter);

    void approve(InfluenceGreetingApproveParameter parameter);
    void reject(InfluenceGreetingRejectParameter parameter);
}
