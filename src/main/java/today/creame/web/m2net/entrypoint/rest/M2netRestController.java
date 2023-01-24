package today.creame.web.m2net.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.m2net.entrypoint.rest.io.M2netNoticeRequest;

@RequiredArgsConstructor
@RestController
public class M2netRestController {
    private final Logger log = LoggerFactory.getLogger(M2netRestController.class);

    @PostMapping("/public/v1/m2net/notice")
    public String notify(@RequestBody M2netNoticeRequest request) {
        log.info("[ Notice ] request: {}", request);
        return "00";
    }
}
