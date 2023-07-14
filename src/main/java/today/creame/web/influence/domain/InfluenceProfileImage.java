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
@Table(name = "influence_profile_image")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString(exclude = {"influence"})
public class InfluenceProfileImage extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "influence_id")
    @Setter
    private Influence influence;

    @Column(name = "file_resource_id")
    private Long fileResourceId;

    @Column(name = "file_resource_uri")
    private String fileResourceUri;

    @Column(
        name = "deleted",
        columnDefinition = "BIT",
        length = 1
    )
    private boolean deleted;

    @Column(name = "order_no")
    private int orderNumber;

    public InfluenceProfileImage(Long fileResourceId, String fileResourceUri, Boolean deleted, int orderNumber) {
        this.fileResourceId = fileResourceId;
        this.fileResourceUri = fileResourceUri;
        this.deleted = deleted;
        this.orderNumber = orderNumber;
    }
}
