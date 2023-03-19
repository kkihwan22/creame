package today.creame.web.coin.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;

@Getter
@ToString
public class MyCoinStatResult {

    private int coins;
    private int bonusCoins;

    public MyCoinStatResult(Member member) {
        this.coins = member.getCoins();
        this.bonusCoins = member.getBonusCoins();
    }
}
