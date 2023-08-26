package today.creame.web.influence.application.model;

import lombok.Getter;

@Getter
public class HotInfluenceParameter {
    private Long influenceId;

    public HotInfluenceParameter(Long influenceId) {
        this.influenceId = influenceId;
    }
}
