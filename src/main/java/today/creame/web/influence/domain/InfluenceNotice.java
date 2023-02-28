package today.creame.web.influence.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;
import today.creame.web.share.model.BaseFileResource;

@NoArgsConstructor
@Entity
@Table(name = "influence_notice")
@Getter
@ToString
public class InfluenceNotice extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "influence_id")
    private Long influenceId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @OneToMany
    @JoinColumn(name = "influence_notice_id")
    private List<InfluenceNoticeAttachFile> attachFiles = new ArrayList<>();

    @Column(name = "deleted")
    private boolean deleted;

    public void attached(BaseFileResource file) {
        this.attachFiles.add(new InfluenceNoticeAttachFile(this.id, file.getFileId(), file.getFileUri()));
    }

    public InfluenceNotice(Long influenceId, String content) {
        this.influenceId = influenceId;
        this.content = content;
    }
}
