package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.applicaton.model.MyReviewResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter @ToString
public class MyReviewsResponse {
    private int writableCount;
    private int writtenCount;
    private List<MyReviewResult> writableReviews;
    private List<MyReviewResult> writtenReviews;

    public MyReviewsResponse(Map<Boolean, List<MyReviewResult>> partition) {
        Optional.ofNullable(partition.get(Boolean.TRUE)).ifPresent(it -> {
            this.writtenCount = it.size();
            this.writtenReviews = it;
        });

        Optional.ofNullable(partition.get(Boolean.FALSE)).ifPresent(it -> {
            this.writableCount = it.size();
            this.writableReviews = it;
        });
    }
}
