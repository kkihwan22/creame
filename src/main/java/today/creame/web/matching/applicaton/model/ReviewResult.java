package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingReview;
import today.creame.web.matching.domain.QMatching;
import today.creame.web.matching.domain.QMatchingReview;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.QMember;

import java.time.LocalDateTime;

@Getter
@ToString
public class ReviewResult {
    private Long matchingId;
    private LocalDateTime matchingStartDateTime;
    private LocalDateTime matchingEndDateTime;
    private Long reviewId;
    private Long memberId;
    private String nickname;
    private int rate;
    private boolean answered = false;
    private String reviewKinds;
    private String category;
    private String reviewContent;
    private String replyContent;
    private LocalDateTime reviewDateTime;
    private LocalDateTime replyDateTime;
    private int likeCount;

    public ReviewResult(MatchingReview review) {
        Matching matching = review.getMatching();
        Member member = matching.getMember();

        this.matchingId = matching.getId();
        this.matchingStartDateTime = matching.getStartDateTime();
        this.matchingEndDateTime = matching.getEndedDateTime();
        this.reviewId = review.getId();
        this.memberId = member.getId();
        this.nickname = member.getNickname();
        this.rate = review.getRate();
        this.reviewKinds = review.getReviewKinds().name();
        this.category = review.getCategory().name();
        this.reviewContent = review.getContent();
        this.replyContent = review.getReply();
        this.reviewDateTime = review.getWriteDateTime();
        this.replyDateTime = review.getReplyDateTime();
        this.likeCount = review.getLikedCount();

        if (replyContent != null) this.answered = true;
    }
}
