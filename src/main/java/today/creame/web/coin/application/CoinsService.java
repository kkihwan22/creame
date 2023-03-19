package today.creame.web.coin.application;

import today.creame.web.coin.application.model.CoinsUpdateParameter;
import today.creame.web.coin.application.model.MyCoinStatResult;

public interface CoinsService {

    void update(CoinsUpdateParameter parameter);

    MyCoinStatResult getCoinStatByMember(Long id);
}
