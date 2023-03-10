package today.creame.web.m2net.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.m2net.infra.feign.config.M2netHeaderConfig;
import today.creame.web.m2net.infra.feign.io.M2netUserCreateRequest;
import today.creame.web.m2net.infra.feign.io.M2netUserCreateResponse;

@FeignClient(
    name = "M2netUserClient",
    url = "http://passcall.co.kr:25205/memb-mgr",
    configuration = M2netHeaderConfig.class
)
public interface M2netUserClient {

    @PostMapping("/0001")
    ResponseEntity<M2netUserCreateResponse> create(@RequestBody M2netUserCreateRequest request);

    @PutMapping("/{mId}")
    ResponseEntity<M2netUserCreateResponse> update(@PathVariable String mId, @RequestBody M2netUserCreateRequest request);
}