package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class M2netPaymentRequest {

    private String membid;
    private int amount;
    private int coinamt;

    public M2netPaymentRequest(String membid, int amount, int coinamt) {
        this.membid = membid;
        this.amount = amount;
        this.coinamt = coinamt;
    }
}
