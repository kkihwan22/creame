package today.creame.web.coin.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.coin.application.model.CoinsHistoryResult;

import java.time.LocalDateTime;

@Getter @ToString
public class MyCoinHistoryResponse {
    private String coinHistoryType;
    private Integer coins;
    private Integer balance;
    private LocalDateTime eventDateTime;

    public MyCoinHistoryResponse(CoinsHistoryResult result) {
        this.coinHistoryType = result.getType().name();
        this.coins = result.getCoins();
        this.balance = result.getBalance();
        this.eventDateTime = result.getEventDateTime();
    }
}