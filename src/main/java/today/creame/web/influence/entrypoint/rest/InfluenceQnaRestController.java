package today.creame.web.influence.entrypoint.rest;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.influence.application.InfluenceQnaService;
import today.creame.web.influence.entrypoint.rest.io.InfluenceCreateQnaRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class InfluenceQnaRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceQnaRestController.class);
    private final InfluenceQnaService influenceQnaService;

    @PostMapping("/api/v1/influences/{id}/qna")
    public ResponseEntity<Body<SimpleBodyData<String>>> createQuestion(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetails userDetails,
        @RequestBody @Valid InfluenceCreateQnaRequest request, BindingResult bindingResult) {

        this.hasError(bindingResult);

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        influenceQnaService.ask(request.toParameter(id, customUserDetails.getId()));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData("success")));
    }
}
