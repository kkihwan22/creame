package today.creame.web.matching.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.matching.domain.converter.ReviewClaimKindsToStringConverter;
import today.creame.web.matching.domain.converter.ReviewClaimStatusToStringConverter;
import today.creame.web.member.domain.Member;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Table(name = "review_claim")
@DynamicInsert
@DynamicUpdate
@Getter
public class ReviewClaim extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private MatchingReview matchingReview;

    @Convert(converter = ReviewClaimKindsToStringConverter.class)
    @Column(name = "review_claim_kinds")
    private ReviewClaimKinds reviewClaimKinds;

    @Column(name = "reason")
    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter")
    private Member reporter;

    @Convert(converter = ReviewClaimStatusToStringConverter.class)
    @Column(name = "status")
    private ReviewClaimStatus status;

    @Column(name = "comment")
    private String comment;

    public ReviewClaim(MatchingReview review, ReviewClaimKinds kinds, String reason, Long reporterId) {
        this.matchingReview = review;
        this.reviewClaimKinds = kinds;
        this.reason = reason;
        this.reporter = new Member(reporterId);
        this.status = ReviewClaimStatus.IN_PROGRESS;
    }
}
