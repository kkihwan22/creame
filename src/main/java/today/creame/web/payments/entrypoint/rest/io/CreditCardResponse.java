package today.creame.web.payments.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

@NoArgsConstructor
@AllArgsConstructor
@With
@Getter
@Setter
@ToString
public class CreditCardResponse {
    private Boolean usedBillKey;
    private String cardno;
    private Boolean usedAutoCharging;
    private Integer balance;
    private Integer amount;
}
