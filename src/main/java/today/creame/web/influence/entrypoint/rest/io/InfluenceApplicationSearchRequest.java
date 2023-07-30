package today.creame.web.influence.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceApplicationStatus;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class InfluenceApplicationSearchRequest {
    List<Long> ids;
    List<InfluenceApplicationStatus> status;
}
