package today.creame.web.m2net.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.m2net.infra.feign.config.M2netHeaderConfig;
import today.creame.web.m2net.infra.feign.io.M2netCounselorCreateRequest;
import today.creame.web.m2net.infra.feign.io.M2netCounselorCreateResponse;

@FeignClient(
    name = "M2netCounselorClient",
    url = "http://passcall.co.kr:25205/csr- mgr",
    configuration = M2netHeaderConfig.class
)
public interface M2netCounselorClient {

    @PostMapping("/0001")
    ResponseEntity<M2netCounselorCreateResponse> create(@RequestBody M2netCounselorCreateRequest request);
}
