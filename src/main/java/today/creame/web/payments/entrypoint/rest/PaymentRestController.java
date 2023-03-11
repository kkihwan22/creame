package today.creame.web.payments.entrypoint.rest;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.payments.application.PaymentService;
import today.creame.web.payments.application.model.CreditCardResult;
import today.creame.web.payments.entrypoint.rest.io.BillKeyIssueRequest;
import today.creame.web.payments.entrypoint.rest.io.CreditCardResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class PaymentRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(PaymentRestController.class);
    private final PaymentService paymentService;

    @PostMapping("/api/v1/me/payments/bill-key")
    public ResponseEntity<Body<SimpleBodyData<String>>> issueBillKey(
        @RequestBody @Valid BillKeyIssueRequest request, BindingResult bindingResult
    ) {
        log.debug("request: {}", request);
        hasError(bindingResult);
        request.validSerial();
        paymentService.issueBillKey(request.of());
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @GetMapping("/api/v1/me/payments/credit-card")
    public ResponseEntity<Body<CreditCardResponse>> getCreditCard() {
        CreditCardResult result = paymentService.getCreditCard();
        CreditCardResponse response = new CreditCardResponse(result.getCreditCard() != null);

        if (result.getCreditCard() != null) {
            response.withCardno(result.getCreditCard().maskCardno());
            response.withUsedAutoCharging(result.getPreference().isEnabled());

            if (result.getPreference().isEnabled()) {
                response.withAmount(result.getPreference().getAmount());
                response.withBalance(result.getPreference().getBalance());
            }
        }

        log.debug("response: {}", response);
        return ResponseEntity.ok(BodyFactory.success(response));
    }

    // 등록 된 카드 삭제

    // 간편결제

    // push
}