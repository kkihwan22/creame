package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.influence.domain.Category;
import today.creame.web.matching.domain.MatchingReview;
import today.creame.web.matching.domain.ReviewKinds;
import java.time.LocalDateTime;

@Getter
public class ReviewListResponse {
    private Long id;
    private int rate;
    private int likedCount;
    private Category category;
    private ReviewKinds reviewKinds;
    private String content;
    private String nickname;
    private LocalDateTime writeDt;
    private String reply;
    private LocalDateTime replyDt;
    private boolean blocked;
    private String reason;
    private LocalDateTime blockedDt;

    public ReviewListResponse(MatchingReview matchingReview) {
        this.id = matchingReview.getId();
        this.rate = matchingReview.getRate();
        this.likedCount = matchingReview.getLikedCount();
        this.category = matchingReview.getCategory();
        this.reviewKinds = matchingReview.getReviewKinds();
        this.content = matchingReview.getContent();
        this.nickname = matchingReview.getMatching().getMember().getNickname();
        this.writeDt = matchingReview.getWriteDateTime();
        this.reply = matchingReview.getReply();
        this.replyDt = matchingReview.getReplyDateTime();
        this.blocked = matchingReview.isBlocked();
        this.reason = matchingReview.getReason();
        this.blockedDt = matchingReview.getBlockedDateTime();
    }
}
