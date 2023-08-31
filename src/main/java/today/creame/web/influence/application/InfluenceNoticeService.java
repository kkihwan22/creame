package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceAdminNoticeParameter;
import today.creame.web.influence.application.model.InfluenceNoticeParameter;
import today.creame.web.influence.application.model.InfluenceNoticeResult;

public interface InfluenceNoticeService {

    InfluenceNoticeResult get(Long id);

    void createOrUpdate(InfluenceNoticeParameter parameter);
    void createOrUpdateByAdmin(InfluenceAdminNoticeParameter parameter);
}
