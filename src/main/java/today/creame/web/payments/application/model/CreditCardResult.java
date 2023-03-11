package today.creame.web.payments.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.payments.domain.AutoChargingPreference;
import today.creame.web.payments.domain.CreditCard;

@Getter
@ToString
public class CreditCardResult {
    private CreditCard creditCard;
    private AutoChargingPreference preference;

    public CreditCardResult(CreditCard creditCard, AutoChargingPreference preference) {
        this.creditCard = creditCard;
        this.preference = preference;
    }
}
