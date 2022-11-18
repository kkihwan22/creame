package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HotInfluenceUpdateParameter {
    private Long id;
    private String title;
    private String bannerImageUri;
    private int orderNumber;

    public HotInfluenceUpdateParameter(Long id, String title, String bannerImageUri, int orderNumber) {
        this.id = id;
        this.title = title;
        this.bannerImageUri = bannerImageUri;
        this.orderNumber = orderNumber;
    }
}
