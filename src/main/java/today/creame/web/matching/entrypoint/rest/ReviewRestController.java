package today.creame.web.matching.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import today.creame.web.matching.applicaton.ReviewService;
import today.creame.web.matching.applicaton.model.ReviewClaimParameter;
import today.creame.web.matching.applicaton.model.ReviewReplyParameter;
import today.creame.web.matching.entrypoint.rest.io.ReviewClaimRequest;
import today.creame.web.matching.entrypoint.rest.io.ReviewCreateRequest;
import today.creame.web.matching.entrypoint.rest.io.ReviewReplyRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class ReviewRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(ReviewRestController.class);
    private final ReviewService reviewService;

    @PostMapping("/api/v1/matchings/{id}/reviews")
    public ResponseEntity<Body<SimpleBodyData<String>>> review(@PathVariable Long id, @RequestBody ReviewCreateRequest request, BindingResult bindingResult) {
        log.debug("request: {}", request);
        hasError(bindingResult);
        reviewService.review(request.withMatchingId(id));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PutMapping("/api/v1/reviews/{id}/answers")
    public ResponseEntity<Body<SimpleBodyData<String>>> answer(@PathVariable Long id, @RequestBody ReviewReplyRequest request, BindingResult bindingResult) {
        log.debug("request:{} ", request);
        hasError(bindingResult);
        reviewService.answer(new ReviewReplyParameter(id, request.getContent()));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PutMapping("/api/v1/reviews/{id}/like")
    public ResponseEntity<Body<SimpleBodyData<String>>> like(@PathVariable Long id) {
        reviewService.like(id);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PostMapping("/api/v1/reviews/{id}/claim")
    public ResponseEntity<Body<SimpleBodyData<String>>> claimReview(@PathVariable Long id,
                            @RequestBody ReviewClaimRequest request) {

        reviewService.claim(new ReviewClaimParameter(id, request.getKinds(), request.getReason()));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }
}
