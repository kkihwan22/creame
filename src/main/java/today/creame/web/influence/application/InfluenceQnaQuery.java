package today.creame.web.influence.application;

import java.util.List;
import today.creame.web.influence.application.model.InfluenceQnaQueryParameter;
import today.creame.web.influence.application.model.InfluenceQnaResult;

public interface InfluenceQnaQuery {
    List<InfluenceQnaResult> pagingList(InfluenceQnaQueryParameter parameter);
}
