package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceApplicationStatus;
import today.creame.web.influence.entrypoint.rest.io.InfluenceApplicationSearchRequest;

import java.util.List;

@Getter
@ToString
public class InfluenceApplicationSearchParameter {
    List<InfluenceApplicationStatus> status;

    public InfluenceApplicationSearchParameter (InfluenceApplicationSearchRequest searchRequest) {
        this.status = searchRequest.getStatus();
    }
}
