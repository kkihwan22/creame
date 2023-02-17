package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HotInfluenceUpdateParameter {
    private Long influenceId;
    private String title;
    private String bannerImageUri;
    private int orderNumber;

    public HotInfluenceUpdateParameter(Long influenceId, String title, String bannerImageUri, int orderNumber) {
        this.influenceId = influenceId;
        this.title = title;
        this.bannerImageUri = bannerImageUri;
        this.orderNumber = orderNumber;
    }
}
