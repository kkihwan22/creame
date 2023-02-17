package today.creame.web.home.application;

import java.util.List;
import today.creame.web.home.entrypoint.io.HomeQueryParam;
import today.creame.web.influence.application.model.InfluenceResult;

public interface DisplayQuery {
    List<InfluenceResult> list(HomeQueryParam param);
}
