package today.creame.web.coin.entrypoint.rest.io;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.coin.domain.CoinsHistoryType;

@Getter
@ToString
public class MyCoinHistoryResponse {
    private String coinHistoryType;
    private Integer coins;
    private Integer totalCoins;
    private LocalDateTime historyDateTime;

    public MyCoinHistoryResponse(CoinsHistoryType coinHistoryType, Integer coins, Integer totalCoins, LocalDateTime historyDateTime) {
        this.coinHistoryType = coinHistoryType.name();
        this.coins = coins;
        this.totalCoins = totalCoins;
        this.historyDateTime = historyDateTime;
    }
}