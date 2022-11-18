package today.creame.web.influence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "hot_influence")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class HotInfluence extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    private Long id;

    @Column(name = "extension_number")
    private String extensionNumber;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "title")
    private String title;

    @Column(name = "banner_image_uri")
    private String bannerImageUri;

    @Column(name = "categories")
    private String categories;

    @Column(name = "order_no")
    private int orderNumber;

    public HotInfluence(Long id, String extensionNumber, String nickname, String title, String bannerImageUri, String categories, int orderNumber) {
        this.id = id;
        this.extensionNumber = extensionNumber;
        this.nickname = nickname;
        this.title = title;
        this.bannerImageUri = bannerImageUri;
        this.categories = categories;
        this.orderNumber = orderNumber;
    }
}