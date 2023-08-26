package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;

@Getter
public class InfluenceSearchRequest {
    private Boolean onlyHotInfluence;

    public InfluenceSearchRequest(Boolean onlyHotInfluence) {
        this.onlyHotInfluence = onlyHotInfluence;
    }
}
