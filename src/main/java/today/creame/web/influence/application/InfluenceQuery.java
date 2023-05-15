package today.creame.web.influence.application;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.influence.application.model.InfluenceDetailResult;
import today.creame.web.influence.application.model.InfluenceQuestionResult;
import today.creame.web.influence.application.model.InfluenceResult;

public interface InfluenceQuery {

    HomeInfluenceStatResult queryInfluenceStat();

    List<InfluenceResult> listAll(Pageable pageable);

    List<InfluenceResult> listByCategory(String category, Pageable pageable);

    List<InfluenceResult> listByBookmarked(Long memberId, boolean called);

    List<InfluenceResult> listByInfluences(Set<Long> ids);

    InfluenceResult getInfluence(Long id);

    List<InfluenceQuestionResult> getQuestionsByInfluence(Long influenceId, PageRequest page);

    List<InfluenceQuestionResult> getInfluenceQuestionsByMe(Long influenceId, Long questionerId, PageRequest pageRequest);

    InfluenceDetailResult getDetail(Long id);


}
