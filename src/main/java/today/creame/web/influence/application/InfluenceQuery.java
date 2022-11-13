package today.creame.web.influence.application;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.influence.domain.InfluenceProfileImage;

// todo: 패키지 위치 변경 (home 으로) / InfluenceResult.java 도 함께
public interface InfluenceQuery {
    List<InfluenceResult> listAll(Pageable pageable);
    List<InfluenceResult> listByCategory(String category, Pageable pageable);
    InfluenceResult getInfluence(Long id);
}
