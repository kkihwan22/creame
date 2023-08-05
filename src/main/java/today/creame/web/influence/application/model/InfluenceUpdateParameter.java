package today.creame.web.influence.application.model;

import lombok.Getter;

@Getter
public class InfluenceUpdateParameter {
    private Long influenceId;
    private String nickname;
    private String phoneNumber;

    public InfluenceUpdateParameter(Long influenceId, String nickname, String phoneNumber) {
        this.influenceId = influenceId;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
}
