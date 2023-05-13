package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.applicaton.model.ReviewResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter @ToString
public class ReviewReplyResponse {
    private int answerableCount;
    private int answeredCount;
    private List<ReviewResult> answerableReviews;
    private List<ReviewResult> answeredReviews;

    public ReviewReplyResponse(Map<Boolean, List<ReviewResult>> partition) {
        Optional.ofNullable(partition.get(Boolean.TRUE)).ifPresent(it -> {
            this.answeredCount = it.size();
            this.answeredReviews = it;
        });

        Optional.ofNullable(partition.get(Boolean.FALSE)).ifPresent(it -> {
            this.answerableCount = it.size();
            this.answerableReviews = it;
        });
    }
}
