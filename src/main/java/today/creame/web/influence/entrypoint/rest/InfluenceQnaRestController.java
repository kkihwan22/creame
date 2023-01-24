package today.creame.web.influence.entrypoint.rest;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.influence.application.InfluenceQnaQuery;
import today.creame.web.influence.application.InfluenceQnaService;
import today.creame.web.influence.application.model.InfluenceQnaAnswerParameter;
import today.creame.web.influence.application.model.InfluenceQnaQueryParameter;
import today.creame.web.influence.application.model.InfluenceQnaResult;
import today.creame.web.influence.entrypoint.rest.io.InfluenceQnaContentRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class InfluenceQnaRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceQnaRestController.class);
    private final InfluenceQnaService influenceQnaService;
    private final InfluenceQnaQuery influenceQnaQuery;

    @GetMapping("/public/v1/influences/{id}/qna")
    public ResponseEntity<Body<List<InfluenceQnaResult>>> listByPaging(
        @PathVariable Long id,
        @RequestParam(required = false, defaultValue = "0" ) int page,
        @RequestParam(required = false, defaultValue = "20") int size
    ) {
        List<InfluenceQnaResult> results = influenceQnaQuery.listByPaging(
            new InfluenceQnaQueryParameter(page, size, Sort.unsorted(), false,  null));
        log.debug("results: {}", results);

        return ResponseEntity.ok(BodyFactory.success(results));
    }

    @GetMapping("/api/v1/influences/{id}/qna")
    public ResponseEntity<Body<List<InfluenceQnaResult>>> listByPaging(
        @PathVariable Long id,
        @RequestParam(required = false, defaultValue = "0" ) int page,
        @RequestParam(required = false, defaultValue = "20") int size,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        List<InfluenceQnaResult> results = influenceQnaQuery.listByPaging(
            new InfluenceQnaQueryParameter(page, size, Sort.unsorted(), true, customUserDetails.getId()));
        log.debug("results: {}", results);

        return ResponseEntity.ok(BodyFactory.success(results));
    }

    @PostMapping("/api/v1/influences/{id}/qna")
    public ResponseEntity<Body<SimpleBodyData<String>>> createQuestion(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetails userDetails,
        @RequestBody @Valid InfluenceQnaContentRequest request, BindingResult bindingResult) {
        log.debug("request: {}", request);
        this.hasError(bindingResult);

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        influenceQnaService.ask(request.toParameter(id, customUserDetails.getId()));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData("success")));
    }

    @PostMapping("/api/v1/influences/{id}/qna/{qnaId}/answer")
    public ResponseEntity<Body<SimpleBodyData<String>>> createAnswer(
        @PathVariable Long id,
        @PathVariable Long qnaId,
        @RequestBody @Valid InfluenceQnaContentRequest request, BindingResult bindingResult) {

        log.debug("request: {}", request);
        this.hasError(bindingResult);

        influenceQnaService.answer(new InfluenceQnaAnswerParameter(id, qnaId, request.getContent()));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData("success")));
    }
}
