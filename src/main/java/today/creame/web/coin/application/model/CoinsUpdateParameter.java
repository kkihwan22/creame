package today.creame.web.coin.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.coin.domain.CoinsHistoryType;
import today.creame.web.payments.domain.PaymentsHistoryType;

@Getter
@ToString
public class CoinsUpdateParameter {

    private Long memberId;
    private Integer coins;
    private CoinsHistoryType type;

    public CoinsUpdateParameter(Long memberId, Integer coins, PaymentsHistoryType paymentsHistoryType) {
        this.memberId = memberId;
        this.coins = coins;
        this.type = paymentsHistoryType.getCoinsHistoryType();
    }
}