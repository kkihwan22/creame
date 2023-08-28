package today.creame.web.influence.application.model;

import lombok.Getter;

@Getter
public class HotInfluenceParameter {
    private Long influenceId;
    private String title;
    private boolean enabled;
    private String bannerImageUri;
    private int orderNumber;

    public HotInfluenceParameter(Long influenceId, String title, boolean enabled, String bannerImageUri, int orderNumber) {
        this.influenceId = influenceId;
        this.title = title;
        this.enabled = enabled;
        this.bannerImageUri = bannerImageUri;
        this.orderNumber = orderNumber;
    }
}
