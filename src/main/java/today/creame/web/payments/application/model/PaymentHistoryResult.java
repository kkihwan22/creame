package today.creame.web.payments.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.payments.domain.PaymentsHistory;
import today.creame.web.payments.domain.PaymentsHistoryStatus;

import java.time.LocalDateTime;

@Getter @ToString
public class PaymentHistoryResult {
    private String method;
    private int amount;
    private PaymentsHistoryStatus status;
    private LocalDateTime paidDateTime;

    public PaymentHistoryResult(PaymentsHistory history) {
        this.method = history.getPaymentMethod().getLabel();
        this.amount = history.getAmount();
        this.status = history.getStatus();
        this.paidDateTime = history.getCreatedDateTime();
    }
}