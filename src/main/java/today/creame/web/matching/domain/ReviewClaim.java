package today.creame.web.matching.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class ReviewClaim {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private MatchingReview matchingReview;
    private ReviewClaimKinds reviewClaimKinds;
    private String reason;
    private Long reporter;
    private ReviewClaimStatus status;
    private String comment;
}
