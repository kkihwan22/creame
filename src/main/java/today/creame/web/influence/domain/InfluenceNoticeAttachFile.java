package today.creame.web.influence.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "influence_notice_attach_file")
@Getter
@ToString
public class InfluenceNoticeAttachFile extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "influence_notice_id")
    private Long influenceNoticeId;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "file_uri")
    private String fileUri;

    @Column(name = "deleted")
    private boolean deleted;

    public InfluenceNoticeAttachFile(Long influenceNoticeId, Long fileId, String fileUri) {
        this.influenceNoticeId = influenceNoticeId;
        this.fileId = fileId;
        this.fileUri = fileUri;
    }
}
