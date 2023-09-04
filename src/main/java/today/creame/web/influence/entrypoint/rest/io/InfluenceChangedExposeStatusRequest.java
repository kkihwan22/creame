package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter @ToString
public class InfluenceChangedExposeStatusRequest {

    private boolean status;
}
