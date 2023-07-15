package today.creame.web.matching.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import today.creame.web.coin.domain.CoinsHistoryType;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.Influence;
import today.creame.web.matching.domain.converter.MatchingProgressStatusToStringConverter;
import today.creame.web.matching.domain.converter.PaidTypeToStringConverter;
import today.creame.web.matching.exception.DuplicateReviewException;
import today.creame.web.matching.exception.IllegalAccessMatchingException;
import today.creame.web.member.domain.Member;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;
import today.creame.web.share.support.SecurityContextSupporter;

@NoArgsConstructor
@Entity
@Table(name = "matching")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString(exclude = {"matchingReviews"})
public class Matching extends BaseCreatedAndUpdatedDateTime {
    private final static Logger log = LoggerFactory.getLogger(Matching.class);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "call_id")
    private String callId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "influence_id")
    private Influence influence;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Convert(converter = MatchingProgressStatusToStringConverter.class)
    @Column(name = "progress")
    private MatchingProgressStatus status;

    @Convert(converter = PaidTypeToStringConverter.class)
    @Column(name = "paid_type")
    private PaidType paidType;

    @Column(name = "start_dt")
    private LocalDateTime startDateTime;

    @Column(name = "end_dt")
    private LocalDateTime endedDateTime;

    @Column(name = "deferred")
    private boolean deferred;

    @Column(name = "used_coins")
    private Integer usedCoins;

    @OneToMany(mappedBy = "matching", cascade = CascadeType.PERSIST)
    private List<MatchingReview> matchingReviews = new ArrayList<>();

    public Matching(Influence influence, Member member, String callId, MatchingProgressStatus status, LocalDateTime startDateTime, LocalDateTime endedDateTime, boolean deferred, Integer usedCoins, PaidType paidType) {
        this.influence = influence;
        this.member = member;
        this.callId = callId;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endedDateTime = endedDateTime;
        this.deferred = deferred;
        this.usedCoins = usedCoins;
        this.paidType = paidType;
    }

    public void end(MatchingProgressStatus status, LocalDateTime endedDateTime, int usedCoins) {
        this.status = status;
        this.endedDateTime = endedDateTime;
        this.usedCoins = usedCoins;
        this.member.updateCoins(CoinsHistoryType.USING, usedCoins);
    }

    public void addReview(int rate, Category category, ReviewKinds kinds, String content) {
        if (!this.member.getId().equals(SecurityContextSupporter.getId())) {
            log.error("matching member: {}, login member: {}", id, member.getId());
            throw new IllegalAccessMatchingException();
        }

        if (matchingReviews.size() > 0) {
            log.error("이미 작성한 리뷰입니다.");
            throw new DuplicateReviewException();
        }

        this.matchingReviews.add(new MatchingReview(this, rate, category, kinds, content));
        this.influence.registerReview(rate);
    }
}
