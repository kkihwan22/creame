package today.creame.web.home.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.home.entrypoint.io.HomeQueryParam;
import today.creame.web.influence.application.HotInfluenceQuery;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.HotInfluenceResult;
import today.creame.web.influence.application.model.InfluenceResult;

@RequiredArgsConstructor
@Service
public class HomeDisplayQueryImpl implements HomeDisplayQuery {
    private final HotInfluenceQuery hotInfluenceQuery;
    private final InfluenceQuery influenceQuery;

    @Override
    public List<HotInfluenceResult> queryEnabledInfluenceList() {
        return hotInfluenceQuery.getEnabledHotInfluenceList();
    }

    @Override
    public HomeInfluenceStatResult queryInfluenceStat() {
        return influenceQuery.queryInfluenceStat();
    }

    @Override
    public List<InfluenceResult> query(HomeQueryParam param) {
        return param.getType().getService().list(param);
    }
}
