package today.creame.web.admin.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
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
import today.creame.web.admin.domain.converters.QnaCategoryToStringConverter;
import today.creame.web.admin.domain.converters.QnaProgressStatusToStringConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

@NoArgsConstructor
@Entity
@Table(name = "qna")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class Qna extends BaseCreatedAndUpdatedDateTimeWithAudit {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Convert(converter = QnaCategoryToStringConverter.class)
    @Column(name = "category")
    private QnaCategory category;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @OneToMany(mappedBy = "qna", fetch = LAZY)
    private List<QnaAttachedFile> attachedFiles;

    @Convert(converter = QnaProgressStatusToStringConverter.class)
    @Column(name = "status")
    private QnaProgressStatus status;

    @Column(name = "reply", columnDefinition = "text")
    private String reply;

    public static Qna createNew(QnaCategory category, String title, String content, List<QnaAttachedFile> attachedFiles) {
        Qna qna = new Qna();
        qna.category = category;
        qna.title = title;
        qna.content = content;
        qna.attachedFiles = attachedFiles;
        qna.status = QnaProgressStatus.REQUEST;
        return qna;
    }
}
