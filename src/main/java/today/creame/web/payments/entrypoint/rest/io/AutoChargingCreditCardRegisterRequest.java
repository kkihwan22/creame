package today.creame.web.payments.entrypoint.rest.io;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AutoChargingCreditCardRegisterRequest {
    @NotNull
    private Integer amount;

    @Valid
    private CreditCardRequest creditCard;
}
