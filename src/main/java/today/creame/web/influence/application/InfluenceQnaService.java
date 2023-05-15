package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceAnswerParameter;
import today.creame.web.influence.application.model.InfluenceQuestionParameter;

public interface InfluenceQnaService {

    void question(InfluenceQuestionParameter parameter);

    void answer(InfluenceAnswerParameter parameter);
}
