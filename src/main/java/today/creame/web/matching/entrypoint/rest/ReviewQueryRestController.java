package today.creame.web.matching.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.matching.applicaton.ReviewQueryService;
import today.creame.web.matching.applicaton.model.ReviewSearchParameter;
import today.creame.web.matching.domain.MatchingReview;
import today.creame.web.matching.entrypoint.rest.io.ReviewListResponse;
import today.creame.web.matching.entrypoint.rest.io.ReviewSearchRequest;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.PageBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ReviewQueryRestController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/admin/v1/reviews/{matchingId}")
    public ResponseEntity<PageBody<ReviewListResponse>> list(@PathVariable Long matchingId, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        Page<MatchingReview> matchingReviewPage = reviewQueryService.listByMatchingId(matchingId, pageable);
        List<ReviewListResponse> responses = CollectionUtils.emptyIfNull(matchingReviewPage.getContent()).stream().map(ReviewListResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(BodyFactory.success(responses, matchingReviewPage.getTotalElements()));

    }

    @GetMapping("/admin/v1/reviews")
    public ResponseEntity<PageBody<ReviewListResponse>> listByMember(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable, ReviewSearchRequest request) {
        Page<MatchingReview> matchingReviewPage = reviewQueryService.list(new ReviewSearchParameter(request), pageable);
        List<ReviewListResponse> responses = CollectionUtils.emptyIfNull(matchingReviewPage.getContent()).stream().map(ReviewListResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(BodyFactory.success(responses, matchingReviewPage.getTotalElements()));

    }
}
