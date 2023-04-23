package today.creame.web.influence.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import today.creame.web.influence.application.model.HotInfluenceResult;
import today.creame.web.influence.domain.HotInfluence;

public interface HotInfluenceQuery {

    List<HotInfluenceResult> getEnabledHotInfluenceList();

    Page<HotInfluence> list(Pageable pageable);
}
