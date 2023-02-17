package today.creame.web.home.application;

import java.util.List;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.influence.application.model.HotInfluenceResult;

public interface HomeDisplayQuery {
    List<HotInfluenceResult> queryEnabledInfluenceList();

    HomeInfluenceStatResult queryInfluenceStat();
}
