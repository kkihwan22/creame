package today.creame.web.payments.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.payments.domain.AutoChargingPreference;
import today.creame.web.payments.domain.CreditCard;

@Getter
@ToString
public class AutoChargingParameter {
    private AutoChargingPreference preference;
    private CreditCard creditCard;

    public AutoChargingParameter(AutoChargingPreference preference, CreditCard creditCard) {
        this.preference = preference;
        this.creditCard = creditCard;
    }
}
