package today.creame.web.influence.entrypoint.rest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceDetailResult;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.influence.entrypoint.rest.io.InfluenceReviewStatResponse;
import today.creame.web.matching.applicaton.MatchingQueryService;
import today.creame.web.matching.applicaton.ReviewQueryService;
import today.creame.web.matching.applicaton.model.MatchingHistoryResult;
import today.creame.web.matching.applicaton.model.ReviewKindStatResult;
import today.creame.web.matching.applicaton.model.ReviewResult;
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
    // TODO: 페이징 처리
    @GetMapping("/api/v1/influences-bookmark")
    public ResponseEntity<Body<List<InfluenceResult>>> getBookmarkedInfluences() {
        Long me = SecurityContextSupporter.getId();
        List<InfluenceResult> results = influenceQuery.listByBookmarked(me, false);
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }

    // 바톰메뉴 (단골- 최근 통화 인플루언스) / p.78
    @GetMapping("/api/v1/influences-recently")
    public ResponseEntity<Body<List<InfluenceResult>>> getRecentlyInfluences() {
        // TODO: test 필요. 연동 확인 후 안되었으면 변경
        // 회원입장에서 최근 통화한 목록이 필요... /me 밑으로 이동해도 될 것 같음
        // MatchingResult 를 보내면 될 듯
        List<MatchingHistoryResult> matchingHistoryResults = matchingQueryService.recentlyMyMatchingList();

        Set<Long> sets = matchingHistoryResults.stream().map(MatchingHistoryResult::getInfluenceId).collect(Collectors.toSet());
        List<InfluenceResult> results = influenceQuery.listByInfluences(sets);
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }


    @GetMapping("/public/v1/influences/{id}/review-stat")
    public ResponseEntity<Body<InfluenceReviewStatResponse>> getReviewOfInfluence(@PathVariable Long id) {
        List<ReviewKindStatResult> results = reviewQueryService.getInfluenceReviewKindStat(id);
        return ResponseEntity.ok(BodyFactory.success(new InfluenceReviewStatResponse(results)));
    }

    @GetMapping("/public/v1/influences/{id}/reviews")
    public ResponseEntity<Body<List<ReviewResult>>> getInfluenceReviews(
        @PathVariable Long id, @RequestParam(required = false, defaultValue = "desc") String direction) {
        Order order = direction.toUpperCase().equals("DESC") ? Order.DESC : Order.ASC;
        List<ReviewResult> results = reviewQueryService.getInfluenceReviews(id, order);
        return ResponseEntity.ok(BodyFactory.success(results));
    }

    @GetMapping("/admin/v1/influence/{id}")
    public ResponseEntity<Body<InfluenceDetailResult>> getDetail(@PathVariable Long id) {
        InfluenceDetailResult influenceDetailResult = influenceQuery.getDetail(id);
        return ResponseEntity.ok(BodyFactory.success(influenceDetailResult));
    }
}