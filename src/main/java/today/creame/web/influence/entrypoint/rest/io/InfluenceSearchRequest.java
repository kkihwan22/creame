package today.creame.web.influence.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.creame.web.influence.domain.Rank;

@Getter
@AllArgsConstructor
public class InfluenceSearchRequest {
    private Boolean onlyHotInfluence;
    private String nickname;
    private Rank rank;

}