package today.creame.web.influence.application.model;

import lombok.Getter;
import today.creame.web.influence.domain.Influence;

@Getter
public class HotInfluenceTargetResult {
    private Long id;
    private String name;
    private String nickname;

    public HotInfluenceTargetResult(Influence influence) {
        this.id = influence.getId();
        this.name = influence.getName();
        this.nickname = influence.getNickname();
    }
}
