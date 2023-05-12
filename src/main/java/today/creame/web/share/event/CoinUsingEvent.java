package today.creame.web.share.event;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.payments.domain.PaymentsHistory;
import today.creame.web.payments.domain.PaymentsHistoryStatus;

@Getter
@ToString
public class CoinUsingEvent {

    private Long memberId;
    private Integer amount;
    private Integer coins;

    public CoinUsingEvent(Long memberId, Integer amount, Integer coins) {
        this.memberId = memberId;
        this.amount = amount;
        this.coins = coins;
    }
}
