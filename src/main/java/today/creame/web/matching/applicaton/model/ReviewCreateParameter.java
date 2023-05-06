package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.matching.domain.ReviewKinds;

@Getter @ToString
public class ReviewCreateParameter {
    private Long matchingId;
    private int rate;
    private Category category;
    private ReviewKinds reviewKinds;
    private String content;

    public ReviewCreateParameter(Long matchingId, int rate, Category category, ReviewKinds reviewKinds, String content) {
        this.matchingId = matchingId;
        this.rate = rate;
        this.category = category;
        this.reviewKinds = reviewKinds;
        this.content = content;
    }
}
