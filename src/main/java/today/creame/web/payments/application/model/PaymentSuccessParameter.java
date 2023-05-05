package today.creame.web.payments.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.payments.domain.PaymentMethod;

@Getter
@ToString
public class PaymentSuccessParameter {
    private String membid;
    private String oid;
    private String tid;
    private int amount;
    private int coinamt;
    private PaymentMethod paymentMethod;

    public PaymentSuccessParameter(String membid, String oid, String tid, int amount, int coinamt, PaymentMethod paymentMethod) {
        this.membid = membid;
        this.oid = oid;
        this.tid = tid;
        this.amount = amount;
        this.coinamt = coinamt;
        this.paymentMethod = paymentMethod;
    }
}