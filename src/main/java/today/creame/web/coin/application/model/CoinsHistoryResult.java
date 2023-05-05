package today.creame.web.coin.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.coin.domain.CoinsHistory;
import today.creame.web.coin.domain.CoinsHistoryType;

import java.time.LocalDateTime;

@Getter @ToString
public class CoinsHistoryResult {
    private CoinsHistoryType type;
    private int coins;
    private int balance;
    private LocalDateTime eventDateTime;
    public CoinsHistoryResult(CoinsHistory history) {
        this.type = history.getType();
        this.coins = history.getCoins();
        this.balance = history.getBalanceCoins();
        this.eventDateTime = history.getCreatedDateTime();
    }
}