package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.entrypoint.rest.io.HotInfluenceUpdateRequest;

@Getter
@ToString
public class HotInfluenceUpdateParameter {
    private Long id;
    private String title;
    private String bannerImageUri;
    private int orderNumber;

    public HotInfluenceUpdateParameter(Long id, HotInfluenceUpdateRequest request) {
        this.id = id;
        this.title = request.getTitle();
        this.bannerImageUri = request.getBannerImageUri();
        this.orderNumber = request.getOrderNumber();
    }
}
