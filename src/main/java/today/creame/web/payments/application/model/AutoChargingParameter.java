package today.creame.web.payments.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.payments.domain.AutoPaymentPreference;
import today.creame.web.payments.domain.CreditCard;

@Getter
@ToString
public class AutoChargingParameter {
    private AutoPaymentPreference preference;
    private CreditCard creditCard;

    public AutoChargingParameter(AutoPaymentPreference preference, CreditCard creditCard) {
        this.preference = preference;
        this.creditCard = creditCard;
    }
}
