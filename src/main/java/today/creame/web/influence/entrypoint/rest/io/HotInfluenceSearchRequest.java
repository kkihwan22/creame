package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;

@Getter
public class HotInfluenceSearchRequest {
    private Boolean isEnabled;

    public HotInfluenceSearchRequest(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
