package today.creame.web.m2net.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.m2net.infra.feign.config.M2netHeaderConfig;
import today.creame.web.m2net.infra.feign.io.M2netBillKeyIssueRequest;
import today.creame.web.m2net.infra.feign.io.M2netBillKeyIssueResponse;
import today.creame.web.m2net.infra.feign.io.M2netPaymentRequest;
import today.creame.web.m2net.infra.feign.io.M2netPrecallRequest;
import today.creame.web.m2net.infra.feign.io.M2netPrecallResponse;
import today.creame.web.m2net.infra.feign.io.M2netRemoveBillKeyRequest;
import today.creame.web.m2net.infra.feign.io.M2netSimpleResponse;

@FeignClient(
    name = "M2netClient",
    url = "${m2net.host}/etc-mgr",
    configuration = M2netHeaderConfig.class
)
public interface M2netClient {

    @PutMapping("/${m2net.cpid}/drconn")
    ResponseEntity<M2netPrecallResponse> preCall(
        @RequestBody M2netPrecallRequest request
    );

    @PostMapping("/${m2net.cpid}/autopayreg")
    ResponseEntity<M2netBillKeyIssueResponse> issueBillKey(
        @RequestBody M2netBillKeyIssueRequest request
    );

    @PostMapping("/${m2net.cpid}/autopay_delete")
    ResponseEntity<M2netSimpleResponse> removeBillKey(@RequestBody M2netRemoveBillKeyRequest request);

    @PostMapping("/${m2net.cpid}/autopay_request")
    ResponseEntity<M2netSimpleResponse> requestPayment(@RequestBody M2netPaymentRequest request);
}
