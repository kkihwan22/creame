package today.creame.web.curation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "hot_influence")
@Getter @ToString
public class HotInfluence extends BaseCreatedAndUpdatedDateTime {

    @Id
    private Long id;

    @Column(name = "extension_number")
    private String extensionNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "banner_image_uri")
    private String bannerImageUri;

    @Column(name = "categories")
    private String categories;

    @Column(name = "order_no")
    private int orderNumber;
}
