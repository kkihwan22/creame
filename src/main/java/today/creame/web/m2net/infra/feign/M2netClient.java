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
    url = "http://passcall.co.kr:25205/etc-mgr",
    configuration = M2netHeaderConfig.class
)
public interface M2netClient {

    // TODO: '0001' -> cpId pathvariable 로 변경

    @PutMapping("/0001/drconn")
    ResponseEntity<M2netPrecallResponse> preCall(
        @RequestBody M2netPrecallRequest request
    );

    @PostMapping("/0001/autopayreg")
    ResponseEntity<M2netBillKeyIssueResponse> issueBillKey(
        @RequestBody M2netBillKeyIssueRequest request
    );

    @PostMapping("/0001/autopay_delete")
    ResponseEntity<M2netSimpleResponse> removeBillKey(@RequestBody M2netRemoveBillKeyRequest request);

    @PostMapping("/0001/autopay_request")
    ResponseEntity<M2netSimpleResponse> requestPayment(@RequestBody M2netPaymentRequest request);
}
