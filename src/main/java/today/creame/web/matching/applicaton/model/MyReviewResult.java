package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingReview;

import java.time.LocalDateTime;
import java.util.Map;

@Getter @ToString
public class MyReviewResult extends MatchingHistoryResult{
    private boolean answered = false;
    private String reviewKinds;
    private String category;
    private String reviewContent;
    private String replyContent;
    private LocalDateTime reviewDateTime;
    private LocalDateTime replyDateTime;
    private int likeCount;
    private boolean liked;
    private int rate;
    private boolean blocked;
    private LocalDateTime blockedDateTime;

    public MyReviewResult(Matching matching, Map<Long, Boolean> mapReviewLiked) {
        super(matching);
        if (matching.getMatchingReviews().size() > 0 ) {
            this.answered = true;
            MatchingReview review = matching.getMatchingReviews().get(0);
            this.reviewKinds = review.getReviewKinds().name();
            this.category = review.getCategory().name();
            this.reviewContent = review.getContent();
            this.reviewDateTime = review.getWriteDateTime();
            this.replyContent = review.getReply();
            this.replyDateTime = review.getReplyDateTime();
            this.likeCount = review.getLikedCount();
            this.rate = review.getRate();
            this.blocked = review.isBlocked();
            this.blockedDateTime = review.getBlockedDateTime();
            this.liked = mapReviewLiked.getOrDefault(review.getId(), false);
        }
    }
}
