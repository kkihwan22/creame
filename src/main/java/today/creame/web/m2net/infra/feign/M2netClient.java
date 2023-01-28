package today.creame.web.m2net.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.m2net.infra.feign.config.M2netHeaderConfig;
import today.creame.web.m2net.infra.feign.io.M2netPrecallRequest;
import today.creame.web.m2net.infra.feign.io.M2netPrecallResponse;

@FeignClient(
    name = "M2netClient",
    url = "http://passcall.co.kr:25205/etc- mgr",
    configuration = M2netHeaderConfig.class
)
public interface M2netClient {

    @PutMapping("/0001/drconn")
    ResponseEntity<M2netPrecallResponse> preCall(@RequestBody M2netPrecallRequest request);
}
