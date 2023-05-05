package today.creame.web.payments.application;

import today.creame.web.payments.application.model.PaymentHistoryResult;

import java.util.List;

public interface PaymentQueryService {

    List<PaymentHistoryResult> history(int since);
}
