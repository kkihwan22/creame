package today.creame.web.matching.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "review_liked")
@DynamicInsert
@DynamicUpdate
@Getter @ToString
public class ReviewLiked extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "liked")
    private boolean liked;

    public void liked() {
        liked = !(liked);
    }

    public ReviewLiked(Long reviewId, Long memberId) {
        this.reviewId = reviewId;
        this.memberId = memberId;
        this.liked = true;
    }
}
