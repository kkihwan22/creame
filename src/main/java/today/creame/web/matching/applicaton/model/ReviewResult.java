package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.Review;
import today.creame.web.matching.domain.ReviewBlocked;

@Getter
@ToString
public class ReviewResult {
    private Long matchingId;
    private Long influenceId;
    private Long memberId;
    private Review review;
    private ReviewBlocked reviewBlocked;

    public ReviewResult(Matching matching) {
        this.matchingId = matching.getId();
        this.influenceId = matching.getInfluenceId();
        this.memberId = matching.getMemberId();
        this.review = matching.getReview();
        this.reviewBlocked = matching.getBlocked();
    }
}
