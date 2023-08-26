package today.creame.web.influence.entrypoint.rest;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class HotInfluenceDeleteRequest {

    @NotNull
    private Long influenceId;

    public HotInfluenceDeleteRequest(Long influenceId) {
        this.influenceId = influenceId;
    }

}
