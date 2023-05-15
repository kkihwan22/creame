package today.creame.web.influence.domain;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
import today.creame.web.influence.domain.converter.InfluenceQnaStatusToStringConverter;
import today.creame.web.influence.exception.BadRequestAnswerException;
import today.creame.web.member.domain.Member;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

@NoArgsConstructor
@Entity
@Table(name = "influence_qna")
@DynamicInsert
@DynamicUpdate
@Getter @ToString(exclude = {
    "influence", "questioner"
})
public class InfluenceQna extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(
        targetEntity = Influence.class,
        fetch = LAZY
    )
    @JoinColumn(name = "influence_id")
    private Influence influence;

    @ManyToOne(
        targetEntity = Member.class,
        fetch = LAZY
    )
    @JoinColumn(name = "questioner_id")
    private Member questioner;

    @Convert(converter = InfluenceQnaStatusToStringConverter.class)
    @Column(name = "status")
    private QnaStatus status;

    @AttributeOverrides({
        @AttributeOverride(name = "content", column = @Column(name = "content", columnDefinition = "TEXT")),
        @AttributeOverride(name = "updatedDateTime", column = @Column(name = "updated_content_dt")),
    })
    @Embedded
    private Content questions;

    @AttributeOverrides({
        @AttributeOverride(name = "content", column = @Column(name = "answer", columnDefinition = "TEXT")),
        @AttributeOverride(name = "updatedDateTime", column = @Column(name = "answered_dt")),
    })
    @Embedded
    private Content answers;

    public InfluenceQna(
        Long influenceId,
        Long questionerId,
        String content
    ) {
        this.influence = new Influence(influenceId);
        this.questioner = new Member(questionerId);
        this.questions = new Content(content);
        this.status = QnaStatus.ASK;
    }

    public void answer(Long id, String content) {
        if (!this.influence.getId().equals(id)) {
            throw new BadRequestAnswerException();
        }

        this.answers = new Content(content);
        this.status = QnaStatus.ANSWER;
        this.influence.answer();
    }
}
