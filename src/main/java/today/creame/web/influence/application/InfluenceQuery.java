package today.creame.web.influence.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.influence.application.model.*;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.InfluenceQna;

import java.util.List;
import java.util.Set;

public interface InfluenceQuery {

    HomeInfluenceStatResult queryInfluenceStat();

    List<InfluenceResult> listAll(Pageable pageable);

    List<InfluenceResult> listByCategory(String category, Pageable pageable);

    List<InfluenceResult> listByBookmarked(Long memberId, boolean called);

    List<InfluenceResult> listByMatchedRecently(Long memberId, boolean called);

    List<InfluenceResult> listByCalling(Pageable pageable);

    List<InfluenceResult> listByInfluences(Set<Long> ids);

    InfluenceResult getInfluence(Long id);

    List<InfluenceQuestionResult> getQuestionsByInfluence(Long influenceId, PageRequest page);

    List<InfluenceQuestionResult> getInfluenceQuestionsByMe(Long influenceId, Long questionerId, PageRequest pageRequest);

    List<InfluenceQuestionResult> getMyQuestions(Long questionerId);

    List<InfluenceQuestionResult> getInfluenceQuestions(Long influenceId);

    InfluenceDetailResult getDetail(Long id);

    Page<InfluenceListResult> getList(Pageable pageable, InfluenceSearchParameter parameter);

    List<InfluenceResult> listByKeyword(Category category, String nickname, Pageable pageable);

    Page<InfluenceQna> qnaList(InfluenceQnaSearchParameter parameter, Pageable pageable);
}
