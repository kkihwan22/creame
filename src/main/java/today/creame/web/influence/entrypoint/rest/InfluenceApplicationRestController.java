package today.creame.web.influence.entrypoint.rest;

import java.util.ArrayList;
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
import today.creame.web.influence.exception.BadRequestException;
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

    @PatchMapping("/admin/v1/influence/applications/approve")
    public ResponseEntity<Body<SimpleBodyData<List<String>>>> approve(@RequestBody InfluenceApplicationSearchRequest request) {
        List<Long> ids = request.getIds();
        if(CollectionUtils.isEmpty(ids)) {
            throw new BadRequestException();
        }

        Long failCount = 0L;
        for(Long id : ids) {
            try {
                influenceApplicationService.approve(id);
            }catch (Exception e) {
                e.printStackTrace();
                failCount++;
            }
        }

        List<String> response = new ArrayList<>();
        Long duplicateCount = influenceApplicationQuery.existDuplicates(ids);
        response.add(duplicateCount + failCount + "건 실패하였습니다.");
        response.add(ids.size() - duplicateCount  + "건 성공하였습니다.");

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(response)));
    }

    @PatchMapping("/admin/v1/influence/applications/duplicate/approve")
    public ResponseEntity<Body<SimpleBodyData<List<String>>>> duplicateApprove(@RequestBody InfluenceApplicationSearchRequest request) {
        List<Long> ids = request.getIds();
        if(CollectionUtils.isEmpty(ids)) {
            throw new BadRequestException();
        }
        Long failCount = 0L;
        for(Long id : ids) {
            try {
                influenceApplicationService.duplicateApprove(id);
            }catch(Exception e) {
                e.printStackTrace();
                failCount++;
            }
        }
        List<String> response = new ArrayList<>();
        response.add(failCount + "건 실패하였습니다.");
        response.add(ids.size() - failCount  + "건 성공하였습니다.");

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(response)));
    }

    @PatchMapping("/admin/v1/influence/applications/reject")
    public ResponseEntity<Body<SimpleBodyData<List<String>>>> reject(@RequestBody InfluenceApplicationSearchRequest request) {
        List<Long> ids = request.getIds();
        if(CollectionUtils.isEmpty(ids)) {
            throw new BadRequestException();
        }
        Long failCount = 0L;
        for(Long id : ids) {
            try {
                influenceApplicationService.reject(id);
            }catch(Exception e) {
                e.printStackTrace();
                failCount++;
            }
        }

        List<String> response = new ArrayList<>();
        response.add(failCount + "건 실패하였습니다.");
        response.add(ids.size() - failCount  + "건 성공하였습니다.");

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(response)));
    }

    @GetMapping("/admin/v1/influence/applications/{id}")
    public ResponseEntity<Body<InfluenceApplicationDetailResult>> getDetail(@PathVariable Long id) {
        InfluenceApplicationDetailResult result = influenceApplicationQuery.getDetail(id);
        return ResponseEntity.ok(BodyFactory.success(result));
    }
}