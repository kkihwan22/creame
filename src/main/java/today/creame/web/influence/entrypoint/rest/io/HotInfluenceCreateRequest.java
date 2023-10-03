package today.creame.web.influence.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import today.creame.web.influence.application.model.HotInfluenceParameter;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotInfluenceCreateRequest {

    @NotNull
    private Long influenceId;
    private String bannerName;
    private String title;
    private boolean enabled;
    private String bannerImageUri;
    @NotNull
    private Integer orderNumber;

    public HotInfluenceParameter toParameter() {
        return new HotInfluenceParameter(this.influenceId, this.bannerName, this.title, this.enabled, this.bannerImageUri, this.orderNumber);
    }
}
