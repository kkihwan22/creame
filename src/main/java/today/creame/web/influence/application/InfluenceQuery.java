package today.creame.web.influence.application;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import today.creame.web.influence.application.model.InfluenceQnaQueryParameter;
import today.creame.web.influence.application.model.InfluenceQnaResult;
import today.creame.web.influence.application.model.InfluenceResult;

public interface InfluenceQuery {
    List<InfluenceResult> listAll(Pageable pageable);

    List<InfluenceResult> listByCategory(String category, Pageable pageable);

    List<InfluenceResult> listByBookmarked(Long memberId, boolean called);

    List<InfluenceResult> listByInfluences(Set<Long> ids);

    InfluenceResult getInfluence(Long id);

    List<InfluenceQnaResult> pagingQnas(InfluenceQnaQueryParameter parameter);
}
