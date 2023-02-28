package today.creame.web.influence.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;
import today.creame.web.share.model.BaseFileResource;

@NoArgsConstructor
@Entity
@Table(name = "influence_notice")
@DynamicInsert
@DynamicUpdate
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

    @OneToMany(mappedBy = "influenceNotice", cascade = CascadeType.ALL)
    private List<InfluenceNoticeAttachFile> attachFiles = new ArrayList<>();

    @Column(name = "deleted")
    private boolean deleted;

    public void attached(BaseFileResource file) {
        InfluenceNoticeAttachFile attachFile = new InfluenceNoticeAttachFile(this, file.getFileId(), file.getFileUri());
        this.attachFiles.add(attachFile);
        attachFile.setInfluenceNotice(this);
    }

    public void delete() {
        this.deleted = true;
    }

    public InfluenceNotice(Long influenceId, String content) {
        this.influenceId = influenceId;
        this.content = content;
    }
}
