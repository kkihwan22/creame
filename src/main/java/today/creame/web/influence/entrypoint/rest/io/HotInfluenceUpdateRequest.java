package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.application.model.HotInfluenceUpdateParameter;

@Getter
@ToString
public class HotInfluenceUpdateRequest {
    private String title;
    private String bannerImageUri;
    private int orderNumber;

    public HotInfluenceUpdateParameter of(Long id) {
        return new HotInfluenceUpdateParameter(
            id,
            this.title,
            this.bannerImageUri,
            this.orderNumber);
    }
}