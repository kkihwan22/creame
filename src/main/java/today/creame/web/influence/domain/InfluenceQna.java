package today.creame.web.influence.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

@NoArgsConstructor
@Entity
@Table(name = "influence_qna")
@DynamicInsert
@DynamicUpdate
@Getter @ToString
public class InfluenceQna extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "influence_id")
    private Long influenceId;

    @Column(name = "questioner_id")
    private Long questionerId;

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
}
