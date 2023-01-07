package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceCreateParameter;
import today.creame.web.share.domain.OnOffCondition;

public interface InfluenceService {

    Long create(InfluenceCreateParameter parameter);

    void patchConnectionStatus(Long id, OnOffCondition status);
}
