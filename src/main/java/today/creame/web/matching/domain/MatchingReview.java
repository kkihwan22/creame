package today.creame.web.matching.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.converter.CategoryToStringConverter;
import today.creame.web.matching.domain.converter.ReviewKindsToStringConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "matching_review")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class MatchingReview extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matching_id")
    @Setter
    private Matching matching;

    @Column(name = "rate")
    private int rate;

    @Column(name = "liked")
    private int likedCount;

    @Convert(converter = CategoryToStringConverter.class)
    @Column(name = "category")
    private Category category;

    @Convert(converter = ReviewKindsToStringConverter.class)
    @Column(name = "kinds")
    private ReviewKinds reviewKinds;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "write_dt")
    private LocalDateTime writeDateTime;

    @Column(name = "reply", columnDefinition = "text")
    private String reply;

    @Column(name = "reply_dt")
    private LocalDateTime replyDateTime;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "reason")
    private String reason;

    @Column(name = "blocked_dt")
    private LocalDateTime blockedDateTime;
}
