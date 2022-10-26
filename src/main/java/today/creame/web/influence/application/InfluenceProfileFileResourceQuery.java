package today.creame.web.influence.application;

import java.util.List;
import today.creame.web.influence.application.model.InfluenceProfileImageFileResourceResult;

public interface InfluenceProfileFileResourceQuery {

    List<InfluenceProfileImageFileResourceResult> findInfluenceProfileImages(List<Long> ids);
}
