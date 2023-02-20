package today.creame.web.influence.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.influence.domain.converter.GreetingsProgressStatusToStringConverter;
import today.creame.web.influence.exception.IllegalReRequestGreetingsHistoryException;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

@NoArgsConstructor
@Entity
@Table(name = "influence_greetings_history")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class InfluenceGreetingsHistory extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Influence.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "influence_id")
    private Influence influence;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "file_uri")
    private String fileUri;

    @Convert(converter = GreetingsProgressStatusToStringConverter.class)
    @Column(name = "status")
    private GreetingsProgressStatus status;

    @Column(name = "reason")
    private String reason;

    public InfluenceGreetingsHistory(Long influenceId, Long fileId, String fileUri) {
        this.influence = new Influence(influenceId); // TODO: 해당 생성자는 private로 숨기고, factory 메소드를 제공하는 쪽으로 변경
        this.fileId = fileId;
        this.fileUri = fileUri;
        this.status = GreetingsProgressStatus.REQUEST;
    }

    public void reRequest(Long fileId, String fileUri) {
        if (this.status != GreetingsProgressStatus.REQUEST) {
            throw new IllegalReRequestGreetingsHistoryException();
        }
        this.fileId = fileId;
        this.fileUri = fileUri;
    }

    public void approve() {
        if (this.status != GreetingsProgressStatus.REQUEST) {
            throw new IllegalReRequestGreetingsHistoryException();
        }

        this.influence.putGreetings(this.fileId, this.fileUri);
        this.status = GreetingsProgressStatus.APPROVAL;
    }
}
