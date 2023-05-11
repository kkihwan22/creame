package today.creame.web.matching.applicaton;

import today.creame.web.matching.applicaton.model.ReviewCreateParameter;
import today.creame.web.matching.applicaton.model.ReviewReplyParameter;

public interface ReviewService {

    void review(ReviewCreateParameter parameter);
    void answer(ReviewReplyParameter parameter);
}
