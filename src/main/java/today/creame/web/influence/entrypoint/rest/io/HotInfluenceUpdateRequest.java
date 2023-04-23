package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class HotInfluenceUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String bannerImageUri;

    private int orderNumber;

    public HotInfluenceUpdateRequest(String title, String bannerImageUri, int orderNumber) {
        this.title = title;
        this.bannerImageUri = bannerImageUri;
        this.orderNumber = orderNumber;
    }
}