package today.creame.web.m2net.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.m2net.infra.feign.config.M2netHeaderConfig;
import today.creame.web.m2net.infra.feign.io.*;

@FeignClient(
    name = "M2netCounselorClient",
    url = "${m2net.host}/csr-mgr",
    configuration = M2netHeaderConfig.class
)
public interface M2netCounselorClient {

    @PostMapping("/${m2net.cpid}")
    ResponseEntity<M2netCounselorCreateResponse> create(
        @RequestBody M2netCounselorCreateRequest request
    );

    @PutMapping("/{cid}")
    ResponseEntity on(
        @PathVariable String cid,
        @RequestBody M2netCounselorStateRequest request
    );

    @PutMapping("/{cid}")
    ResponseEntity off(
        @PathVariable String cid,
        @RequestBody M2netCounselorStateRequest request
    );

    @PutMapping("/{cid}")
    ResponseEntity<M2netCounselorCreateResponse> updateInfluenceInfo(
            @PathVariable String cid,
            @RequestBody M2netCounselorInfoUpdateRequest request
    );

    @PutMapping("/{cid}")
    ResponseEntity<M2netCounselorCreateResponse> changePrice(
            @PathVariable String cid,
            @RequestBody M2netCounselorChangePriceRequest request
    );
}
