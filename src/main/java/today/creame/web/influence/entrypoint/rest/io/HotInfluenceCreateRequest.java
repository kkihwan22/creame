package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.influence.application.model.HotInfluenceParameter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class HotInfluenceCreateRequest {

    @NotNull
    private Long influenceId;

    @NotBlank
    private String title;

    @NotBlank
    private String bannerImageUri;

    private int orderNumber;

    public HotInfluenceParameter of() {
        return new HotInfluenceParameter(
                this.influenceId,
                this.title,
                this.bannerImageUri,
                this.orderNumber);
    }
}
