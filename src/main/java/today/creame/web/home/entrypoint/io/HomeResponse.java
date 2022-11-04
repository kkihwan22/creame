package today.creame.web.home.entrypoint.io;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.home.application.model.HomeHotInfluenceResult;
import today.creame.web.home.application.model.HomeInfluenceStatResult;

@Getter @ToString
public class HomeResponse {
    private final List<HomeHotInfluenceResult> hots;
    private final HomeInfluenceStatResult stat;

    public HomeResponse(List<HomeHotInfluenceResult> hots, HomeInfluenceStatResult stat) {
        this.hots = hots;
        this.stat = stat;
    }
}
