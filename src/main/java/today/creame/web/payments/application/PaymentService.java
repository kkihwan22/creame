package today.creame.web.payments.application;

import today.creame.web.payments.application.model.CreditCardResult;
import today.creame.web.payments.application.model.PaymentFailureParameter;
import today.creame.web.payments.application.model.PaymentSuccessParameter;
import today.creame.web.payments.domain.AutoChargingPreference;
import today.creame.web.payments.domain.CreditCard;
import today.creame.web.payments.domain.PaymentsHistoryStatus;

public interface PaymentService {
    void issueBillKey(CreditCard creditCard);

    void removeBillKey();

    void enableAutoCharging(AutoChargingPreference preference);

    void disabledAutoCharging();

    void changePaymentPassword(String oPass, String nPass);

    CreditCardResult getCreditCard();

    void payByBillKey(String paymentPassword, int amount);

    void paySuccess(PaymentsHistoryStatus type, PaymentSuccessParameter parameter);

    void payFailure(PaymentFailureParameter parameter);
}
