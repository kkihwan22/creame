package today.creame.web.payments.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import today.creame.web.payments.application.PaymentService;
import today.creame.web.payments.domain.PaymentsHistoryStatus;
import today.creame.web.payments.entrypoint.rest.io.ReceiptRequest;

@RequiredArgsConstructor
@Controller
public class PaymentController {
    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    @RequestMapping("/m2net/payments/notice")
    public String receiptPaymentResult(@RequestBody ReceiptRequest request) {
        logger.info(" [ payment ]  request : {}", request);

        if (request.isSuccess()) {
            paymentService.paySuccess(PaymentsHistoryStatus.COMPLETED, request.toSuccess());
        } else {
            paymentService.payFailure(request.toFailed());
        }
        return "result";
    }
}