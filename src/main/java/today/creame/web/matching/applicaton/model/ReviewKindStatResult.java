package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.ReviewKinds;

@Getter
@ToString
public class ReviewKindStatResult {
    private ReviewKinds kinds;
    private int total;

    public ReviewKindStatResult(ReviewKinds kinds, int total) {
        this.kinds = kinds;
        this.total = total;
    }
}

