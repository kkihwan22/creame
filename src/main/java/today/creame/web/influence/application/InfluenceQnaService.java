package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceQnaAnswerParameter;
import today.creame.web.influence.application.model.InfluenceQnaQuestionParameter;

public interface InfluenceQnaService {

    void ask(InfluenceQnaQuestionParameter parameter);

    void answer(InfluenceQnaAnswerParameter parameter);
}
