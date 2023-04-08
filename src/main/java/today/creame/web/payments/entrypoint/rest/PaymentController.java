package today.creame.web.payments.entrypoint.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {

    @RequestMapping("/m2net/payments/notice")
    public String receiptPaymentResult() {
        return "result";
    }
}
