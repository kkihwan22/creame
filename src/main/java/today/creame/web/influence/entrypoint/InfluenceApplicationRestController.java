package today.creame.web.influence.entrypoint;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.application.InfluenceApplicationService;
import today.creame.web.influence.application.InfluenceApplicationServiceImpl;
import today.creame.web.influence.entrypoint.model.InfluenceApplicationRegisterRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class InfluenceApplicationRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceApplicationRestController.class);
    private final InfluenceApplicationService influenceApplicationService;

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
    public void list(@RequestParam(name = "statues", defaultValue = "") String statues) {
        log.debug("request param: {}", statues);

        // is black 이면, InfluenceApplicationStatus 전체를 리스트로
        // 아니면 "," 로 스프릿 한 후 리스트로
        // 잘못된 값이 있으면 exception
    }
}
