package today.creame.web.matching.applicaton;

import today.creame.web.matching.applicaton.model.MatchingParameter;

public interface MatchingService {
    void start(MatchingParameter parameter);

    void end(MatchingParameter parameter);

    // 리뷰
    // 답글
}
