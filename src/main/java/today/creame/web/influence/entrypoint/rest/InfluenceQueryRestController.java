package today.creame.web.influence.entrypoint.rest;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceDetailResult;
import today.creame.web.influence.application.model.InfluenceListResult;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.influence.entrypoint.rest.io.InfluenceMeResponse;
import today.creame.web.influence.entrypoint.rest.io.InfluenceReviewStatResponse;
import today.creame.web.influence.entrypoint.rest.io.InfluenceSearchRequest;
import today.creame.web.matching.applicaton.MatchingQueryService;
import today.creame.web.matching.applicaton.ReviewQueryService;
import today.creame.web.matching.applicaton.model.*;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.PageBody;
import today.creame.web.share.support.SecurityContextSupporter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping("/api/v1/me/influence")
    public ResponseEntity<Body<InfluenceMeResponse>> getMe() {
        Long id = SecurityContextSupporter.getId();
        InfluenceResult result = influenceQuery.getInfluence(id);
        log.debug("result: {}", result);

        List<MatchingStatisticsResult> list = matchingQueryService.getConsultationHoursPerMonth(new MatchingStatisticsParameter(id, LocalDate.now()));
        return ResponseEntity.ok(BodyFactory.success(new InfluenceMeResponse(result, list)));
    }

    @GetMapping("/api/v1/influences-bookmark")
    public ResponseEntity<Body<List<InfluenceResult>>> getBookmarkedInfluences(
            @RequestParam(required = false) String conn
    ) {
        Long me = SecurityContextSupporter.getId();
        List<InfluenceResult> results = influenceQuery.listByBookmarked(me, StringUtils.equalsIgnoreCase(conn, "on"));
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }

    // 바톰메뉴 (단골- 최근 통화 인플루언스) / p.78
    @GetMapping("/api/v1/influences-recently")
    public ResponseEntity<Body<List<InfluenceResult>>> getRecentlyInfluences(
            @RequestParam(required = false) String conn
    ) {
        Long me = SecurityContextSupporter.getId();
        List<InfluenceResult> results = influenceQuery.listByMatchedRecently(me, StringUtils.equalsIgnoreCase(conn, "on"));
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

    @GetMapping("/admin/v1/influence")
    public ResponseEntity<PageBody<InfluenceListResult>> list(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable,
            InfluenceSearchRequest searchRequest){

        Page<InfluenceListResult> influencePage = influenceQuery.getList(pageable, searchRequest.getOnlyHotInfluence());

        return ResponseEntity.ok(BodyFactory.success(influencePage.getContent(), influencePage.getTotalElements()));
    }
}