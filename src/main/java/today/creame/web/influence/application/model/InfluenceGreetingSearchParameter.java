package today.creame.web.influence.application.model;

import lombok.Getter;
import today.creame.web.influence.domain.GreetingsProgressStatus;
import today.creame.web.influence.entrypoint.rest.io.InfluenceGreetingSearchRequest;

@Getter
public class InfluenceGreetingSearchParameter {
    private GreetingsProgressStatus status;

    public InfluenceGreetingSearchParameter(InfluenceGreetingSearchRequest request) {
        this.status = request.getStatus();
    }
}
