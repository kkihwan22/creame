package today.creame.web.influence.application;

import today.creame.web.influence.application.model.HotInfluenceParameter;
import today.creame.web.influence.application.model.HotInfluenceUpdateParameter;

public interface HotInfluenceService {
    Long create(HotInfluenceParameter parameter);
    void update(HotInfluenceUpdateParameter parameter);
    void enabled(Long id);
}
