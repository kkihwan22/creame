package today.creame.web.influence.application.query;

import java.util.List;
import today.creame.web.influence.application.model.InfluenceApplicationResult;
import today.creame.web.influence.domain.InfluenceApplicationStatus;

public interface InfluenceApplicationQuery {
    List<InfluenceApplicationResult> list(List<InfluenceApplicationStatus> influenceApplicationStatuses);
}
