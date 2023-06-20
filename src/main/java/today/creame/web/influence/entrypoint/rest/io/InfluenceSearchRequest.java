package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;

@Getter
public class InfluenceSearchRequest {
    Boolean onlyHotInfluence;

    public InfluenceSearchRequest(Boolean onlyHotInfluence) {
        this.onlyHotInfluence = onlyHotInfluence;
    }
}
