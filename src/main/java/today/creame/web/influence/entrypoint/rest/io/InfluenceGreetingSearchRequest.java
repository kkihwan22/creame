package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.influence.domain.GreetingsProgressStatus;

@Getter
public class InfluenceGreetingSearchRequest {
    private GreetingsProgressStatus status;

    public InfluenceGreetingSearchRequest(GreetingsProgressStatus status) {
        this.status = status;
    }
}
