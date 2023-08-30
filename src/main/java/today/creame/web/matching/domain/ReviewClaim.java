package today.creame.web.matching.domain;

import today.creame.web.member.domain.Member;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

public class ReviewClaim {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private MatchingReview matchingReview;
    private ReviewClaimKinds reviewClaimKinds;
    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter")
    private Member reporter;
    private ReviewClaimStatus status;
    private String comment;

    public ReviewClaim(MatchingReview review, ReviewClaimKinds kinds, String reason, Long reporterId) {
        this.matchingReview = review;
        this.reviewClaimKinds = kinds;
        this.reason = reason;
        this.reporter = new Member(reporterId);
        this.status = ReviewClaimStatus.IN_PROGRESS;
    }
}
