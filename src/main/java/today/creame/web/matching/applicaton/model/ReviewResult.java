package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.MatchingReview;

@Getter
@ToString
public class ReviewResult {
    private Long matchingId;
    private Long influenceId;
    private Long memberId;
    private MatchingReview matchingReview;
    //private ReviewBlocked reviewBlocked;

    public ReviewResult(MatchingReview matchingReview) {
        this.matchingId = matchingReview.getId();
        this.influenceId = matchingReview.getMatching().getInfluence().getId();
        this.memberId = matchingReview.getMatching().getMember().getId();
        this.matchingReview = matchingReview;
        //this.reviewBlocked = review.getBlocked();
    }
}
