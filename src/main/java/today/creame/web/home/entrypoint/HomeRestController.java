package today.creame.web.home.entrypoint;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.home.application.HomeDisplayServiceFactory;
import today.creame.web.home.application.HomeHotInfluenceQuery;
import today.creame.web.home.application.HomeInfluenceStatQuery;
import today.creame.web.home.application.model.DisplayParameter;
import today.creame.web.home.application.model.HomeHotInfluenceResult;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.home.entrypoint.io.HomeResponse;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;

@RequiredArgsConstructor
@RestController
public class HomeRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(HomeRestController.class);
    private final HomeHotInfluenceQuery homeHotInfluenceQuery;
    private final HomeInfluenceStatQuery homeInfluenceStatQuery;
    private final HomeDisplayServiceFactory homeDisplayServiceFactory;

    @GetMapping("/public/v1/home")
    public ResponseEntity<Body<HomeResponse>> Home() {
        List<HomeHotInfluenceResult> hots = homeHotInfluenceQuery.getHomeHotInfluence();
        HomeInfluenceStatResult stat = homeInfluenceStatQuery.getHomeInfluenceStat();
        return ResponseEntity.ok(BodyFactory.success(new HomeResponse(hots, stat)));
    }

    @GetMapping("/public/v1/home/influences")
    public ResponseEntity<Body<List<InfluenceResult>>> listInfluence(
        @RequestParam(defaultValue = "all", required = false) String listType,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String keyword) {

        List<InfluenceResult> results = homeDisplayServiceFactory.factory(listType).list(page, size, new DisplayParameter(category, keyword));
        log.debug("results: {}",results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }
}
