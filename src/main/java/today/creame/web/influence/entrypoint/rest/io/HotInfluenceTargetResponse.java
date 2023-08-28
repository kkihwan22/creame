package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.influence.application.model.HotInfluenceTargetResult;

@Getter
public class HotInfluenceTargetResponse {
    private Long id;
    private String name;
    private String nickname;

    public HotInfluenceTargetResponse(HotInfluenceTargetResult result) {
        this.id = result.getId();
        this.name = result.getName();
        this.nickname = result.getNickname();
    }
}
