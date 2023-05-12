package today.creame.web.matching.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import today.creame.web.matching.applicaton.ReviewService;
import today.creame.web.matching.applicaton.model.ReviewReplyParameter;
import today.creame.web.matching.entrypoint.rest.io.ReviewCreateRequest;
import today.creame.web.matching.entrypoint.rest.io.ReviewReplyRequest;
import today.creame.web.share.entrypoint.BaseRestController;

@RequiredArgsConstructor
@RestController
public class ReviewRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(ReviewRestController.class);
    private final ReviewService reviewService;

    @PostMapping("/api/v1/matchings/{id}/reviews")
    public void review(@PathVariable Long id, @RequestBody ReviewCreateRequest request, BindingResult bindingResult) {
        log.debug("request: {}", request);
        hasError(bindingResult);
        reviewService.review(request.withMatchingId(id));
    }

    @PutMapping("/api/v1/reviews/{id}/answers")
    public void answer(@PathVariable Long id, @RequestBody ReviewReplyRequest request, BindingResult bindingResult) {
        log.debug("request:{} ", request);
        hasError(bindingResult);
        reviewService.answer(new ReviewReplyParameter(id, request.getContent()));
    }
}
