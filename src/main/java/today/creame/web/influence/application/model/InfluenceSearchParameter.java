package today.creame.web.influence.application.model;

import lombok.Getter;
import today.creame.web.influence.domain.Rank;
import today.creame.web.influence.entrypoint.rest.io.InfluenceSearchRequest;

@Getter
public class InfluenceSearchParameter {
    private Boolean onlyHotInfluence;
    private String nickname;
    private Rank rank;

    public InfluenceSearchParameter(InfluenceSearchRequest request) {
        this.onlyHotInfluence = request.getOnlyHotInfluence();
        this.nickname = request.getNickname();
        this.rank = request.getRank();
    }
}