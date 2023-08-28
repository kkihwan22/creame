package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class HotInfluenceUpdateRequest {

    @NotBlank
    private String title;
    private String bannerImageUri;
    private boolean enabled;
    @NotNull
    private Integer orderNumber;

    public HotInfluenceUpdateRequest(String title, String bannerImageUri, boolean enabled, int orderNumber) {
        this.title = title;
        this.bannerImageUri = bannerImageUri;
        this.enabled = enabled;
        this.orderNumber = orderNumber;
    }
}