package today.creame.web.health.entrypoint.rest;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRestController {
    private final Logger LOG = LoggerFactory.getLogger(HealthRestController.class);

    @GetMapping("/_health")
    public ResponseEntity<Map<String,String>> checkHealth() {
        LOG.info("[health] check.");
        final Map<String, String> responseBody = new HashMap<>();
        responseBody.put("result", "success");
        responseBody.put("service", "creame-web-service");
        return ResponseEntity.ok(responseBody);
    }
}