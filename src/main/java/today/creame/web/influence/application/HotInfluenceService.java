package today.creame.web.influence.application;

import today.creame.web.influence.application.model.HotInfluenceDetailResult;
import today.creame.web.influence.application.model.HotInfluenceParameter;
import today.creame.web.influence.application.model.HotInfluenceUpdateParameter;

import java.util.List;

public interface HotInfluenceService {
    HotInfluenceDetailResult getDetail(Long id);
    Long create(HotInfluenceParameter parameter);
    void update(HotInfluenceUpdateParameter parameter);
    void enabled(Long id);
    void delete(Long influenceId);
    void updateNickname(Long influenceId, String nickname);
    void updateCategories(Long influenceId, List<String> categories);
}
