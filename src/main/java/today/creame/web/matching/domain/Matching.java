package today.creame.web.matching.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import javax.persistence.Column;
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
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

@NoArgsConstructor
@Entity
@Table(name = "matching")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class Matching extends BaseCreatedAndUpdatedDateTimeWithAudit {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "influence_id")
    private Long influenceId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "status")
    private MatchingStatus progressStatus;

    @Column(name = "req_dt")
    private LocalDateTime requestDateTime;

    @Column(name = "res_dt")
    private LocalDateTime responseDateTime;

    @Column(name = "end_dt")
    private LocalDateTime endedDateTime;

    @Column(name = "tel_company")
    private TelecomCompanyCode telecomCompany;

    @Column(name = "used_coins")
    private Integer usedCoins;

    @Embedded
    private Review review;

    @Embedded
    private ReviewBlocked blocked;
}
