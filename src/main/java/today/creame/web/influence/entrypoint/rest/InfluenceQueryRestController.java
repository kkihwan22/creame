package today.creame.web.influence.entrypoint.rest;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.influence.entrypoint.rest.io.InfluenceReviewStatResponse;
import today.creame.web.matching.applicaton.MatchingQueryService;
import today.creame.web.matching.applicaton.ReviewQueryService;
import today.creame.web.matching.applicaton.model.MatchingResult;
import today.creame.web.matching.applicaton.model.ReviewKindStatResult;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@RestController
public class InfluenceQueryRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceQueryRestController.class);
    private final InfluenceQuery influenceQuery;
    private final MatchingQueryService matchingQueryService;
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/public/v1/influences/{id}")
    public ResponseEntity<Body<InfluenceResult>> getInfluence(@PathVariable Long id) {
        InfluenceResult result = influenceQuery.getInfluence(id);
        log.debug("result: {}", result);
        return ResponseEntity.ok(BodyFactory.success(result));
    }

    // 바톰메뉴 (단골- 상담사) / p.78
    @GetMapping("/api/v1/influences-bookmark")
    public ResponseEntity<Body<List<InfluenceResult>>> getBookmarkedInfluences() {
        Long me = SecurityContextSupporter.getId();
        List<InfluenceResult> results = influenceQuery.listByBookmarked(me, false);
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(Collections.emptyList()));
    }

    // 바톰메뉴 (단골- 최근 통화 인플루언스) / p.78
    @GetMapping("/api/v1/influences-recently")
    public ResponseEntity<Body<List<InfluenceResult>>> getRecentlyInfluences() {
        Long me = SecurityContextSupporter.getId();
        List<MatchingResult> matchingResults = matchingQueryService.recentlyMyMatching(me, true);

        Set<Long> sets = matchingResults.stream().map(MatchingResult::getInfluenceId).collect(Collectors.toSet());
        List<InfluenceResult> results = influenceQuery.listByInfluences(sets);
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }

    // 인플루언스 상세 (후기상세 - 상담스타일) / p.15
    @GetMapping("/public/v1/influences/{id}/review-stat")
    public ResponseEntity<Body<InfluenceReviewStatResponse>> getReviewOfInfluence(@PathVariable Long id) {
        List<ReviewKindStatResult> results = reviewQueryService.getReviewKindsStatByInfluence(id);
        return ResponseEntity.ok(BodyFactory.success(new InfluenceReviewStatResponse(results)));
    }

    @GetMapping("/public/v1/influences/{id}/reviews")
    public void getReviewListOfInfluence(
        @PathVariable Long id,
        @RequestParam(required = false, defaultValue = "desc") String direction) {
        reviewQueryService.listReviewByInfluence(id, "createdDateTime|" + direction);
    }
}