package today.creame.web.influence.entrypoint.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class HotInfluenceDeleteRequest {

    @NotNull
    private Long influenceId;

    public HotInfluenceDeleteRequest(Long influenceId) {
        this.influenceId = influenceId;
    }

}
