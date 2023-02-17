package today.creame.web.home.application;

import java.util.List;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.home.entrypoint.io.HomeQueryParam;
import today.creame.web.influence.application.model.HotInfluenceResult;
import today.creame.web.influence.application.model.InfluenceResult;

public interface HomeDisplayQuery {
    List<HotInfluenceResult> queryEnabledInfluenceList();

    HomeInfluenceStatResult queryInfluenceStat();

    List<InfluenceResult> query(HomeQueryParam param);
}
