package today.creame.web.payments.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

@AllArgsConstructor
@With
@Getter
@ToString
public class CreditCardResponse {
    private Boolean usedBillKey;
    private String cardno;
    private Boolean usedAutoCharging;
    private Integer balance;
    private Integer amount;

    public CreditCardResponse(Boolean usedBillKey) {
        this.usedBillKey = usedBillKey;
    }
}
