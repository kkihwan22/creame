package today.creame.web.m2net.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import today.creame.web.m2net.domain.converter.M2netReasonCodeToStringConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "m2net_notice_history")
@DynamicInsert
@Getter
@ToString
public class M2netNoticeHistory extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        name = "telegram",
        columnDefinition = "text"
    )
    private String telegram;

    @Column(
        name = "c_id",
        columnDefinition = "CHAR(5)"
    )
    private String cId;

    @Column(
        name = "m_id"
    )
    private Long mId;

    @Convert(converter = M2netReasonCodeToStringConverter.class)
    @Column(name = "reason")
    private M2netReasonCode reason;

    @Column(name = "event_tm")
    private LocalDateTime eventDateTime;

    public M2netNoticeHistory(String telegram, String cId, Long mId, M2netReasonCode reason, LocalDateTime eventDateTime) {
        this.telegram = telegram;
        this.cId = cId;
        this.mId = mId;
        this.reason = reason;
        this.eventDateTime = eventDateTime;
    }
}