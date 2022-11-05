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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

@NoArgsConstructor
@Entity
@Table(name = "influence_bookmark")
@DynamicInsert
@DynamicUpdate
@Getter @ToString
public class InfluenceBookmark extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "influence_id")
    private Long influenceId;

    @Column(
        name = "bookmarked",
        columnDefinition = "tinyint(1)"
    )
    private boolean bookmarked;

    public InfluenceBookmark(Long memberId, Long influenceId, Long audit) {
        this.memberId = memberId;
        this.influenceId = influenceId;
        this.createdBy = audit;
    }

    public void marked(Long audit) {
        this.updatedBy = audit;
        bookmarked = true;
    }

    public void canceled(Long audit) {
        this.updatedBy = audit;
        bookmarked = false;
    }
}
