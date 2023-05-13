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

    public ReviewResult(MatchingReview matchingReview, Matching matching, Member member) {
        this.matchingId = matching.getId();
        this.reviewId = matchingReview.getId();
        this.memberId = member.getId();
        this.nickname = member.getNickname();
        this.rate = matchingReview.getRate();
        this.reviewKinds = matchingReview.getReviewKinds().name();
        this.category = matchingReview.getCategory().name();
        this.reviewContent = matchingReview.getContent();
        this.replyContent = matchingReview.getReply();
        this.reviewDateTime = matchingReview.getWriteDateTime();
        this.replyDateTime = matchingReview.getReplyDateTime();
        this.likeCount = matchingReview.getLikedCount();

        if (replyContent != null) this.answered = true;
    }
}
