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
@Table(name = "hot_influence")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class HotInfluence extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(
        name = "influence_id"
    )
    private Long influenceId;

    @Column(
        name = "title"
    )
    private String title;

    @Column(
        name = "banner_image_uri"
    )
    private String bannerImageUri;

    @Column(
        name = "nickname"
    )
    private String nickname;

    @Column(
        name = "extension_number"
    )
    private String extensionNumber;

    @Column(
        name = "categories"
    )
    private String categories;

    @Column(
        name = "order_no"
    )
    private int orderNumber;

    @Column(
        name = "enabled"
    )
    private boolean enabled;

    public HotInfluence(Long influenceId, String nickname, String extensionNumber, String categories, String title, boolean enabled, String bannerImageUri, int orderNumber) {
        this.influenceId = influenceId;
        this.nickname = nickname;
        this.extensionNumber = extensionNumber;
        this.categories = categories;
        this.orderNumber = orderNumber;
        this.title = title;
        this.enabled = enabled;
        this.bannerImageUri = bannerImageUri;
    }

    public void enabled(){
        this.enabled = !this.enabled;
    }

    public void changeHotInfluence(String title, String bannerImageUri, int orderNumber, boolean enabled) {
        this.title = title;
        this.bannerImageUri = bannerImageUri;
        this.orderNumber = orderNumber;
        this.enabled = enabled;
    }

    public void changeOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}