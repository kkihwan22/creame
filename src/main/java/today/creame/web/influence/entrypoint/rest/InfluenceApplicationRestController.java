package today.creame.web.influence.entrypoint.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.application.InfluenceApplicationQuery;
import today.creame.web.influence.application.InfluenceApplicationService;
import today.creame.web.influence.application.model.InfluenceApplicationResult;
import today.creame.web.influence.domain.InfluenceApplicationStatus;
import today.creame.web.influence.entrypoint.rest.io.InfluenceApplicationRegisterRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;
import today.creame.web.share.entrypoint.exception.BadRequestParameterException;
import today.creame.web.share.exception.model.ErrorBodyData;

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
    public ResponseEntity<Body<List<InfluenceApplicationResult>>> list(
        @RequestParam(name = "statues", defaultValue = "") String statues) {
        log.debug("request param: {}", statues);

        List<InfluenceApplicationStatus> conditionStatuses =  new ArrayList<>();
        if (StringUtils.isEmpty(statues)) {
            Arrays.stream(InfluenceApplicationStatus.values())
                .forEach(conditionStatuses::add);
        } else {
            List<ErrorBodyData> errors = new ArrayList<>();
            Arrays.stream(statues.split(","))
                .forEach(status -> {
                    try {
                        conditionStatuses.add(InfluenceApplicationStatus.valueOf(status.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        log.info("No enum constant {}", status.toUpperCase());
                        errors.add(new ErrorBodyData(
                            "InfluenceApplicationStatus",
                            "No enum constant value :" + status));
                    }

                    if (!errors.isEmpty()) {
                        throw new BadRequestParameterException(errors);
                    }
                });
        }

        List<InfluenceApplicationResult> result = influenceApplicationQuery.list(conditionStatuses);
        log.debug("result: {}", result);

        return ResponseEntity.ok(BodyFactory.success(result));
    }

    @PatchMapping("/admin/v1/influence/applications/{id}/approve")
    public ResponseEntity<Body<SimpleBodyData<String>>> approve(@PathVariable Long id) {
        influenceApplicationService.approve(id);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/admin/v1/influence/applications/{id}/reject")
    public void reject(@PathVariable Long id) {

    }
}