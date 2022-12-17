package today.creame.web.home.application;

import java.util.List;
import today.creame.web.home.application.model.DisplayParameter;
import today.creame.web.influence.application.model.InfluenceResult;

public interface HomeDisplayService {
    List<InfluenceResult> list(int page, int size, DisplayParameter parameter);
}
