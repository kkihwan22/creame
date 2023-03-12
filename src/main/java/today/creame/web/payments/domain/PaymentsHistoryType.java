package today.creame.web.payments.domain;

import static today.creame.web.coin.domain.CoinsHistoryType.CHARGING;
import static today.creame.web.coin.domain.CoinsHistoryType.REFUNDED;

import lombok.Getter;
import today.creame.web.coin.domain.CoinsHistoryType;

public enum PaymentsHistoryType {

    AUTO_CHARGING(CHARGING),
    PAYMENT_REQUEST(CHARGING),
    PAYMENT_FAILED(null),
    PAYMENT_CANCELED(REFUNDED),
    ;

    @Getter
    private CoinsHistoryType coinsHistoryType;

    PaymentsHistoryType(CoinsHistoryType coinsHistoryType) {
        this.coinsHistoryType = coinsHistoryType;
    }
}