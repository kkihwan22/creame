package today.creame.web.coin.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MyCoinStatResponse {

    private Integer coin;
    private Integer rewardCoin;

    public MyCoinStatResponse(Integer coin, Integer rewardCoin) {
        this.coin = coin;
        this.rewardCoin = rewardCoin;
    }
}
