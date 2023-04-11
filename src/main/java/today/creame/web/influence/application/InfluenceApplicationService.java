package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceApplicationDetailResult;
import today.creame.web.influence.application.model.InfluenceApplicationParameter;

public interface InfluenceApplicationService {

    Long register(InfluenceApplicationParameter parameter);
    void approve(Long id);
    void cancel(Long id);
    void reject(Long id);

}
