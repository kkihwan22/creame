package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceReviewReplyParameter;
import today.creame.web.influence.application.model.InfluenceReviewWriteParameter;

public interface InfluenceReviewService {

    void writeContent(InfluenceReviewWriteParameter parameter);
    void reply(InfluenceReviewReplyParameter parameter);
    void liked(Long id);
}
