package today.creame.web.influence.application.model;

import lombok.Getter;
import today.creame.web.influence.entrypoint.rest.io.InfluenceQnaSearchRequest;

@Getter
public class InfluenceQnaSearchParameter {
    private Long memberId;
    private String nickname;

    public InfluenceQnaSearchParameter(InfluenceQnaSearchRequest request) {
        this.memberId = request.getMemberId();
        this.nickname = request.getNickname();
    }
}
