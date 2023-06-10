package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;

@Getter
public class InfluenceSearchRequest {
    Boolean isHotInfluence;

    public InfluenceSearchRequest(Boolean isHotInfluence) {
        this.isHotInfluence = isHotInfluence;
    }
}
