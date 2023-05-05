package today.creame.web.matching.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.influence.domain.Influence;
import today.creame.web.matching.domain.converter.MatchingProgressStatusToStringConverter;
import today.creame.web.member.domain.Member;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "matching")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class Matching extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "influence_id")
    private Influence influence;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Convert(converter = MatchingProgressStatusToStringConverter.class)
    @Column(name = "progress")
    private MatchingProgressStatus status;

    @Column(name = "start_dt")
    private LocalDateTime startDateTime;

    @Column(name = "end_dt")
    private LocalDateTime endedDateTime;

    @Column(name = "deferred")
    private boolean deferred;

    @Column(name = "used_coins")
    private Integer usedCoins;

    @OneToMany(mappedBy = "matching")
    private List<MatchingReview> matchingReviews = new ArrayList<>();

    public Matching(Influence influence, Member member, MatchingProgressStatus status, LocalDateTime startDateTime, LocalDateTime endedDateTime, boolean deferred, Integer usedCoins) {
        this.influence = influence;
        this.member = member;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endedDateTime = endedDateTime;
        this.deferred = deferred;
        this.usedCoins = usedCoins;
    }

    public void end(MatchingProgressStatus status, LocalDateTime endedDateTime, int usedCoins) {
        this.status = status;
        this.endedDateTime = endedDateTime;
        this.usedCoins = usedCoins;
    }
}
