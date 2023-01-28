package today.creame.web.admin.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "qna_attach")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class QnaAttachedFile extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private Qna qna;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "file_uri")
    private String fileUri;

    public QnaAttachedFile(Long fileId, String fileUri) {
        this.fileId = fileId;
        this.fileUri = fileUri;
    }
}
