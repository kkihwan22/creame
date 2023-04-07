package today.creame.web.influence.entrypoint.rest;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import today.creame.web.influence.application.InfluenceApplicationQuery;
import today.creame.web.influence.application.InfluenceApplicationService;
import today.creame.web.influence.application.model.InfluenceApplicationDetailResult;
import today.creame.web.influence.application.model.InfluenceApplicationResult;
import today.creame.web.influence.application.model.InfluenceApplicationSearchParameter;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.influence.entrypoint.rest.io.InfluenceApplicationRegisterRequest;
import today.creame.web.influence.entrypoint.rest.io.InfluenceApplicationSearchRequest;
import today.creame.web.share.entrypoint.*;

@RequiredArgsConstructor
@RestController
public class InfluenceApplicationRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceApplicationRestController.class);
    private final InfluenceApplicationService influenceApplicationService;
    private final InfluenceApplicationQuery influenceApplicationQuery;

    @PostMapping("/public/v1/influence/applications")
    public ResponseEntity<Body<SimpleBodyData<Long>>> register(
        @RequestBody @Valid InfluenceApplicationRegisterRequest request,
        BindingResult bindingResult) {
        log.debug(" [ request ] : {}", request);
        hasError(bindingResult);

        Long result = influenceApplicationService.register(request.toParameter());
        log.debug("result: {}",result);

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(result)));
    }

    @GetMapping("/admin/v1/influence/applications")
    public ResponseEntity<PageBody<InfluenceApplicationResult>> list(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable,
            @ModelAttribute("searchRequest") InfluenceApplicationSearchRequest searchRequest){
        log.debug("request param: {}", searchRequest);

        Page<InfluenceApplication> influenceApplicationPage = influenceApplicationQuery.list(new InfluenceApplicationSearchParameter(searchRequest), pageable);
        List<InfluenceApplicationResult> results = CollectionUtils.emptyIfNull(influenceApplicationPage.getContent()).stream().map(InfluenceApplicationResult::new).collect(Collectors.toList());
        log.debug("result: {}", results);

        return ResponseEntity.ok(BodyFactory.success(results, influenceApplicationPage.getTotalElements()));
    }

    @PatchMapping("/admin/v1/influence/applications/{id}/approve")
    public ResponseEntity<Body<SimpleBodyData<String>>> approve(@PathVariable Long id) {
        influenceApplicationService.approve(id);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/admin/v1/influence/applications/{id}/reject")
    public ResponseEntity<Body<SimpleBodyData<String>>> reject(@PathVariable Long id) {
        influenceApplicationService.reject(id);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @GetMapping("/admin/v1/influence/applications/{id}")
    public ResponseEntity<Body<InfluenceApplicationDetailResult>> getDetail(@PathVariable Long id) {
        InfluenceApplicationDetailResult result = influenceApplicationService.getDetail(id);
        return ResponseEntity.ok(BodyFactory.success(result));
    }
}