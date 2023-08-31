package today.creame.web.influence.application;

import today.creame.web.influence.application.model.*;
import today.creame.web.influence.domain.Item;
import today.creame.web.influence.domain.SNS;
import today.creame.web.share.domain.OnOffCondition;

public interface InfluenceService {

    Long create(InfluenceCreateParameter parameter);

    void patchConnectionStatus(Long id, OnOffCondition status);

    Item getItem(InfluenceItemParameter parameter);

    void changeItem(InfluenceItemParameter parameter);

    SNS getSNS(Long id);

    void update(SnsParameter parameter);

    void changeInfluenceInfo(InfluenceUpdateParameter parameter);
    void updateBlocked(Long id);
    void updateProfileImages(InfluenceProfileImageUpdateParameter parameter);
    void updateInfluenceInfo(InfluenceUpdateInfoParameter parameter);

}
