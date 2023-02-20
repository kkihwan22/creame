package today.creame.web.influence.entrypoint.rest;

import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.application.InfluenceGreetingHistoryQueryService;
import today.creame.web.influence.application.InfluenceGreetingsHistoryService;
import today.creame.web.influence.application.model.InfluenceGreetingApproveParameter;
import today.creame.web.influence.application.model.InfluenceGreetingCreateParameter;
import today.creame.web.influence.application.model.InfluenceGreetingsHistoryResult;
import today.creame.web.influence.application.model.InfluenceGreetingsQueryParameter;
import today.creame.web.influence.entrypoint.rest.io.GreetingsResponse;
import today.creame.web.influence.entrypoint.rest.io.InfluenceGreetingCreateRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class InfluenceGreetingRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceGreetingRestController.class);
    private final InfluenceGreetingsHistoryService influenceGreetingsHistoryService;
    private final InfluenceGreetingHistoryQueryService influenceGreetingHistoryQueryService;

    @PostMapping("/api/v1/influence/{id}/greetings")
    public ResponseEntity<Body<SimpleBodyData<String>>> createGreetingFile(
        @PathVariable Long id,
        @Valid @RequestBody InfluenceGreetingCreateRequest request, BindingResult bindingResult
    ) {
        hasError(bindingResult);
        influenceGreetingsHistoryService.create(new InfluenceGreetingCreateParameter(id, request.getFileId(), request.getFileUri()));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/admin/v1/influence/{id}/greetings/{requestId}/approve")
    public ResponseEntity<Body<SimpleBodyData<String>>> approve(
        @PathVariable Long id, @PathVariable Long requestId) {
        influenceGreetingsHistoryService.approve(new InfluenceGreetingApproveParameter(id, requestId));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @GetMapping("/api/v1/influence/{id}/greetings/latest")
    public ResponseEntity<Body<GreetingsResponse>> getLatestGreetings(@PathVariable Long id) {
        InfluenceGreetingsHistoryResult result
            = influenceGreetingHistoryQueryService.queryInfluenceId(new InfluenceGreetingsQueryParameter(id));
        log.debug("result: {}", result);

        GreetingsResponse response = Optional.ofNullable(result)
            .map(it -> new GreetingsResponse(it.getFileUri()))
            .orElse(new GreetingsResponse());

        log.debug("response: {}", response);
        return ResponseEntity.ok(BodyFactory.success(response));
    }
}
