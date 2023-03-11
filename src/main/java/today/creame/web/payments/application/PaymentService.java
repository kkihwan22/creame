package today.creame.web.payments.application;

import today.creame.web.payments.application.model.CreditCardResult;
import today.creame.web.payments.domain.AutoChargingPreference;
import today.creame.web.payments.domain.CreditCard;

public interface PaymentService {
    void issueBillKey(CreditCard creditCard);

    void enableAutoCharging(AutoChargingPreference preference);

    void disabledAutoCharging();

    void changePaymentPassword(String oPass, String nPass);

    CreditCardResult getCreditCard();
}
