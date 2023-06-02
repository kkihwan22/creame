package today.creame.web.influence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.influence.exception.BadRequestAnswerException;
import today.creame.web.member.domain.Member;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

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

    @Column(name = "answered")
    private boolean answered;

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
        this.answered = false;
    }

    public void answer(Long id, String content) {
        if (!this.influence.getId().equals(id)) {
            throw new BadRequestAnswerException();
        }

        this.answers = new Content(content);
        this.answered = true;
        this.influence.answer();
    }
}
