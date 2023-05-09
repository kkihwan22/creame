package today.creame.web.payments.domain;

import lombok.Getter;
import today.creame.web.coin.domain.CoinsHistoryType;

import static today.creame.web.coin.domain.CoinsHistoryType.CHARGING;

public enum PaymentsHistoryStatus {

    COMPLETED(CHARGING),
    FAILED(null),
    CANCELED(CoinsHistoryType.CANCELED),
    ;

    @Getter
    private CoinsHistoryType coinsHistoryType;

    PaymentsHistoryStatus(CoinsHistoryType coinsHistoryType) {
        this.coinsHistoryType = coinsHistoryType;
    }
}