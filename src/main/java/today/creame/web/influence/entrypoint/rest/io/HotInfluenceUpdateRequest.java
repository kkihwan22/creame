package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.application.model.HotInfluenceUpdateParameter;

@Getter @ToString
public class HotInfluenceUpdateRequest {

    @NotNull
    private Long influenceId;

    @NotBlank
    private String title;

    @NotBlank
    private String bannerImageUri;

    private int orderNumber;

    public HotInfluenceUpdateParameter of() {
        return new HotInfluenceUpdateParameter(
            influenceId,
            this.title,
            this.bannerImageUri,
            this.orderNumber);
    }
}