package today.creame.web.influence.entrypoint.rest;

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
import today.creame.web.influence.application.HotInfluenceQuery;
import today.creame.web.influence.application.HotInfluenceService;
import today.creame.web.influence.application.model.HotInfluenceUpdateParameter;
import today.creame.web.influence.domain.HotInfluence;
import today.creame.web.influence.entrypoint.rest.io.HotInfluenceListResponse;
import today.creame.web.influence.entrypoint.rest.io.HotInfluenceCreateRequest;
import today.creame.web.influence.entrypoint.rest.io.HotInfluenceUpdateRequest;
import today.creame.web.share.entrypoint.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class HotInfluenceRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(HotInfluenceRestController.class);
    private final HotInfluenceService hotInfluenceService;
    private final HotInfluenceQuery hotInfluenceQuery;

    @PostMapping("/admin/v1/banners/hot")
    public ResponseEntity<Body<SimpleBodyData<Long>>> create(
            @RequestBody @Valid HotInfluenceCreateRequest request, BindingResult bindingResult) {
        log.debug("request: {}", request);
        hasError(bindingResult);

        Long result = hotInfluenceService.create(request.of());
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(result)));
    }

    @PutMapping("/admin/v1/banners/hot/{id}")
    public ResponseEntity<Body<SimpleBodyData<String>>> update(@PathVariable Long id,
        @RequestBody @Valid HotInfluenceUpdateRequest request, BindingResult bindingResult) {
        log.debug("request: {}", request);
        hasError(bindingResult);

        hotInfluenceService.update(new HotInfluenceUpdateParameter(id, request));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @GetMapping("/admin/v1/banners/hot")
    public ResponseEntity<PageBody<HotInfluenceListResponse>> list(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        Page<HotInfluence> hotInfluencePage = hotInfluenceQuery.list(pageable);
        List<HotInfluenceListResponse> response = CollectionUtils.emptyIfNull(hotInfluencePage.getContent()).stream().map(HotInfluenceListResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(BodyFactory.success(response, hotInfluencePage.getTotalElements()));
    }

    @PatchMapping("/admin/v1/banners/hot/{id}/enabled")
    public ResponseEntity<Body<SimpleBodyData<String>>> enabled(@PathVariable Long id) {
        hotInfluenceService.enabled(id);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }
}