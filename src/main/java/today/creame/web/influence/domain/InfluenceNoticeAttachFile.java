package today.creame.web.influence.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
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
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "influence_notice_attach_file")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class InfluenceNoticeAttachFile extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "influence_notice_id")
    @Setter
    private InfluenceNotice influenceNotice;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "file_uri")
    private String fileUri;

    @Column(name = "deleted")
    private boolean deleted;

    public InfluenceNoticeAttachFile(InfluenceNotice influenceNotice, Long fileId, String fileUri) {
        this.influenceNotice = influenceNotice;
        this.fileId = fileId;
        this.fileUri = fileUri;
    }
}
