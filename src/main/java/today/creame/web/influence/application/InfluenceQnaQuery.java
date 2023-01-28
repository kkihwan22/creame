package today.creame.web.influence.application;

import java.util.List;
import today.creame.web.influence.application.model.InfluenceQnaAnswerParameter;
import today.creame.web.influence.application.model.InfluenceQnaQueryParameter;
import today.creame.web.influence.application.model.InfluenceQnaQuestionQueryParameter;
import today.creame.web.influence.application.model.InfluenceQnaResult;

public interface InfluenceQnaQuery {
    List<InfluenceQnaResult> pagingList(InfluenceQnaQueryParameter parameter);

    List<InfluenceQnaResult> pagingListByQuestions(InfluenceQnaQuestionQueryParameter parameter);

    List<InfluenceQnaResult> pagingListByAnswers(InfluenceQnaAnswerParameter parameter);
}
