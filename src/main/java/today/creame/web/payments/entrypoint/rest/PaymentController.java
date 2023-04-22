package today.creame.web.payments.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import today.creame.web.payments.application.PaymentService;
import today.creame.web.payments.application.model.ReceiptParameter;
import today.creame.web.payments.entrypoint.rest.io.ReceiptRequest;

@RequiredArgsConstructor
@Controller
public class PaymentController {
    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    @RequestMapping("/m2net/payments/notice")
    public String receiptPaymentResult(@RequestBody ReceiptRequest request) {
        logger.info(" [ payment ]  request : {}", request);
        paymentService.postPay(ReceiptParameter.paramToPaymentSuccess(request.getMembid(), request.getOid(), request.getTid(),  request.getAmount(), request.getCoinamt(), request.getReqResult(), request.getResultmessage()));

        return "result";
    }
}
