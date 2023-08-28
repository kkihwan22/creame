package today.creame.web.influence.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class HotInfluenceTargetParameter {
    private Long id;
    private String name;
    private String nickname;

    public HotInfluenceTargetParameter(Long id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }
}
