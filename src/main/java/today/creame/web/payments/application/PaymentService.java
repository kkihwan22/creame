package today.creame.web.payments.application;

import today.creame.web.payments.domain.CreditCard;

public interface PaymentService {

    void issueBillKey(CreditCard creditCard);
}
