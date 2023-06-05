package today.creame.web.home.entrypoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.home.application.HomeDisplayQuery;
import today.creame.web.home.application.HomeDisplayServiceFactory;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.home.entrypoint.io.HomeInfluencesResponse;
import today.creame.web.home.entrypoint.io.HomeQueryParam;
import today.creame.web.influence.application.model.HotInfluenceResult;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.matching.applicaton.ReviewQueryService;
import today.creame.web.matching.applicaton.model.ReviewResult;
import today.creame.web.matching.domain.MatchingReviewJapRepository;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;

@RequiredArgsConstructor
@RestController
public class HomeRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(HomeRestController.class);
    private final HomeDisplayQuery homeDisplayQuery;
    private final HomeDisplayServiceFactory homeDisplayServiceFactory; // todo: 개선
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/public/v1/home/top-banner")
    public ResponseEntity<Body<List<HotInfluenceResult>>> enabledHotInfluenceList() {
        List<HotInfluenceResult> results = homeDisplayQuery.queryEnabledInfluenceList();
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }

    @GetMapping("/public/v1/home/influence/stat")
    public ResponseEntity<Body<HomeInfluenceStatResult>> statInfluence() {
        HomeInfluenceStatResult result = homeDisplayQuery.queryInfluenceStat();
        return ResponseEntity.ok(BodyFactory.success(result));
    }

    // TODO: refactoring
    @GetMapping("/public/v1/home/influences")
    public ResponseEntity<Body<List<HomeInfluencesResponse>>> listInfluence(@ModelAttribute HomeQueryParam param) {
        List<InfluenceResult> results = homeDisplayQuery.query(param);
        log.debug("results: {}", results);

        Map<Long, List<ReviewResult>> groupByReviews =
                reviewQueryService.getReviewGroupByInfluences(results.stream()
                        .map(InfluenceResult::getId)
                        .collect(Collectors.toSet()));

        List<HomeInfluencesResponse> responses = results.stream()
                .map(it -> new HomeInfluencesResponse(it, groupByReviews.getOrDefault(it.getId(), Collections.emptyList())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(BodyFactory.success(responses));
    }
}