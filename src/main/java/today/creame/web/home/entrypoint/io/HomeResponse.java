package today.creame.web.home.entrypoint.io;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.influence.application.model.HotInfluenceResult;

@Getter @ToString
public class HomeResponse {
    private final List<HotInfluenceResult> hots;
    private final HomeInfluenceStatResult stat;

    public HomeResponse(List<HotInfluenceResult> hots, HomeInfluenceStatResult stat) {
        this.hots = hots;
        this.stat = stat;
    }
}
