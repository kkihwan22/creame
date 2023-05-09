package today.creame.web.payments.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.payments.domain.PaymentMethod;

@Getter @ToString
public class PaymentFailureParameter extends PaymentSuccessParameter{
    private String reqResult;
    private String resultmessage;

    public PaymentFailureParameter(String membid, String oid, String tid, int amount, int coinamt, PaymentMethod paymentMethod, String reqResult, String resultmessage) {
        super(membid, oid, tid, amount, coinamt, paymentMethod);
        this.reqResult = reqResult;
        this.resultmessage = resultmessage;
    }
}
