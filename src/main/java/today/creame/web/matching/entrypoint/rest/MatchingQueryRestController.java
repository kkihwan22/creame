package today.creame.web.matching.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import today.creame.web.matching.applicaton.MatchingQueryService;
import today.creame.web.matching.applicaton.ReviewQueryService;
import today.creame.web.matching.applicaton.model.*;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.entrypoint.rest.io.MatchingListResponse;
import today.creame.web.matching.entrypoint.rest.io.MatchingSearchRequest;
import today.creame.web.matching.entrypoint.rest.io.MyReviewsResponse;
import today.creame.web.matching.entrypoint.rest.io.ReviewReplyResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.PageBody;
import today.creame.web.share.support.SecurityContextSupporter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MatchingQueryRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(MatchingQueryRestController.class);
    private final MatchingQueryService matchingQueryService;
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/api/v1/me/matching-history")
    public ResponseEntity<Body<List<MatchingHistoryResult>>> getMyMatchingHistory(@RequestParam(name = "since") int since) {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);

        List<MatchingHistoryResult> results = matchingQueryService.listMatching(id, since);
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }

    @GetMapping("/api/v1/me/matching-history/{id}")
    public ResponseEntity<Body<MatchingResult>> getMyMatchingDetail(@PathVariable Long id) {
        log.debug("matching id: {}", id);
        MatchingResult result = matchingQueryService.getMatching(id);
        log.debug("result: {}", result);
        return ResponseEntity.ok(BodyFactory.success(result));
    }

    @GetMapping("/api/v1/me/reviews")
    public ResponseEntity<Body<MyReviewsResponse>> getMyReviews() {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);
        List<MyReviewResult> results = matchingQueryService.listMyReview(id);
        Map<Boolean, List<MyReviewResult>> partition = results.stream().collect(Collectors.partitioningBy(it -> it.isAnswered()));
        log.debug("partition: {}", partition);
        return ResponseEntity.ok(BodyFactory.success(new MyReviewsResponse(partition)));
    }

    @GetMapping("/api/v1/me/influence/reviews")
    public ResponseEntity<Body<ReviewReplyResponse>> getReviewsOfInfluence() {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);
        List<ReviewResult> results = reviewQueryService.getReviewsByInfluence(id);
        Map<Boolean, List<ReviewResult>> partition = results.stream().collect(Collectors.partitioningBy(it -> it.isAnswered()));
        log.debug("partition: {}", partition);
        return ResponseEntity.ok(BodyFactory.success(new ReviewReplyResponse(partition)));
    }

    @GetMapping("/admin/v1/matching/{type}")
    public ResponseEntity<PageBody<MatchingListResponse>> list(@PathVariable String type, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable, MatchingSearchRequest searchRequest) {
        Page<Matching> matchingPage = matchingQueryService.list(new MatchingSearchParameter(type, searchRequest), pageable);
        List<MatchingListResponse> responses = CollectionUtils.emptyIfNull(matchingPage.getContent()).stream().map(MatchingListResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(BodyFactory.success(responses, matchingPage.getTotalElements()));

    }
}
