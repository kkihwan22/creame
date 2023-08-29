package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.ReviewClaimKinds;

@Getter @ToString
public class ReviewClaimParameter {
    private Long reviewId;
    private ReviewClaimKinds kinds;
    private String comment;

    public ReviewClaimParameter(Long reviewId, ReviewClaimKinds kinds, String comment) {
        this.reviewId = reviewId;
        this.kinds = kinds;
        this.comment = comment;
    }
}
