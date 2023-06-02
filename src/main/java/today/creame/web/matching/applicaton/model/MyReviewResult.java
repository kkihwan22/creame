package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingReview;
import today.creame.web.matching.domain.ReviewKinds;

import java.time.LocalDateTime;
import java.util.Optional;

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
    private int rate;
    private boolean blocked;
    private LocalDateTime blockedDateTime;

    public MyReviewResult(Matching matching, String profileImageUrl) {
        super(matching, profileImageUrl);

        if (!matching.getMatchingReviews().isEmpty()) {
            MatchingReview review = matching.getMatchingReviews().get(0);
            this.answered = true;
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
        }
    }
}
