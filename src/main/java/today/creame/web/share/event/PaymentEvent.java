package today.creame.web.share.event;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.payments.domain.PaymentsHistory;
import today.creame.web.payments.domain.PaymentsHistoryStatus;

@Getter
@ToString
public class PaymentEvent {
    private Long memberId;
    private PaymentsHistoryStatus type;
    private Integer amount;
    private Integer coins;

    public PaymentEvent(PaymentsHistory paymentsHistory) {
        this.memberId = paymentsHistory.getMember().getId();
        this.type = paymentsHistory.getStatus();
        this.amount = paymentsHistory.getAmount();
        this.coins = paymentsHistory.getCoins();
    }
}
