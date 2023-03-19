package today.creame.web.coin.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.coin.application.model.MyCoinStatResult;

@Getter
@ToString
public class MyCoinStatResponse {

    private Integer coin;
    private Integer rewardCoin;

    public MyCoinStatResponse(Integer coin, Integer rewardCoin) {
        this.coin = coin;
        this.rewardCoin = rewardCoin;
    }

    public MyCoinStatResponse(MyCoinStatResult result) {
        this.coin = result.getCoins();
        this.rewardCoin = result.getBonusCoins();
    }
}
