package today.creame.web.share.event;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.payments.domain.PaymentsHistoryType;

@Getter
@ToString
public class PaymentEvent {
    private Long memberId;
    private PaymentsHistoryType type;
    private Integer amount;
    private Integer coins;

    public PaymentEvent(Long memberId, PaymentsHistoryType type, Integer amount, Integer coins) {
        this.memberId = memberId;
        this.type = type;
        this.amount = amount;
        this.coins = coins;
    }
}
