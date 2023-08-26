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

    public HotInfluenceParameter toParameter() {
        return new HotInfluenceParameter(this.influenceId);
    }
}
