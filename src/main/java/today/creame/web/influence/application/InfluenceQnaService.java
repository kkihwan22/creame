package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceQnaAnswerParameter;
import today.creame.web.influence.application.model.InfluenceQnaAskParameter;

public interface InfluenceQnaService {

    void ask(InfluenceQnaAskParameter parameter);
    void answer(InfluenceQnaAnswerParameter parameter);
}
