package today.creame.web.influence.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.influence.domain.converter.CategoryToStringConverter;
import today.creame.web.influence.domain.converter.InfluenceReviewKindsToStringConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

@NoArgsConstructor
@Entity
@Table(name = "influence_review")
@DynamicInsert
@DynamicUpdate
@Getter @ToString
public class InfluenceReview extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "influence_id")
    private Long influenceId;

    @Column(name = "reviewer_id")
    private Long reviewerId;

    @Column(name = "matching_id")
    private Long matchingId;

    @Column(name = "rate")
    private int rate;

    @Convert(converter = CategoryToStringConverter.class)
    @Column(name = "category")
    private Category category;

    @Convert(converter = InfluenceReviewKindsToStringConverter.class)
    @Column(name = "review_kinds")
    private InfluenceReviewKinds reviewKinds;

    @AttributeOverrides({
        @AttributeOverride(name = "content", column = @Column(name = "review", columnDefinition = "TEXT")),
        @AttributeOverride(name = "updatedDateTime", column = @Column(name = "review_updated_dt")),
    })
    @Embedded
    private Content contentReview;

    @AttributeOverrides({
        @AttributeOverride(name = "content", column = @Column(name = "reply", columnDefinition = "TEXT")),
        @AttributeOverride(name = "updatedDateTime", column = @Column(name = "reply_updated_dt")),
    })
    @Embedded
    private Content contentReply;

    @Column(name = "liked_count")
    private int likedCount;

    @AttributeOverrides({
        @AttributeOverride(name = "blocked", column = @Column(name = "blocked", columnDefinition = "bit")),
        @AttributeOverride(name = "blockedReason", column = @Column(name = "blocked_reason")),
        @AttributeOverride(name = "blockedDateTime", column = @Column(name = "blocked_dt")),
    })
    @Embedded
    private ReviewBlocked reviewBlocked;
}
