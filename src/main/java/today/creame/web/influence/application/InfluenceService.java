package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceCreateParameter;
import today.creame.web.influence.domain.SNS;
import today.creame.web.share.domain.OnOffCondition;

public interface InfluenceService {

    Long create(InfluenceCreateParameter parameter);

    void patchConnectionStatus(Long id, OnOffCondition status);

    void changeItem(Long id, Integer index);

    SNS getSNS(Long id);

    void update(Long id, SNS sns);
}
