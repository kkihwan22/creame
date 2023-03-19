package today.creame.web.m2net.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.m2net.application.M2netService;
import today.creame.web.m2net.entrypoint.rest.io.M2netUpdateCallStatusRequest;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@RestController
public class M2netRestController {
    private final Logger log = LoggerFactory.getLogger(M2netRestController.class);
    private final M2netService m2netService;

    @PostMapping("/m2net/call-status")  // todo : notice -> receipt 로 변경
    public String updateCallStatus(@RequestBody M2netUpdateCallStatusRequest request) {
        log.info("[ Notice ] request: {}", request);
        m2netService.updateCallStatus(request.of());
        return "00";
    }

    @PutMapping("/api/v1/m2net/pre-call/{id}")
    public ResponseEntity<Body<SimpleBodyData<String>>> preCall(@PathVariable Long id) {
        m2netService.preCall(id, SecurityContextSupporter.get().getId());
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }
}