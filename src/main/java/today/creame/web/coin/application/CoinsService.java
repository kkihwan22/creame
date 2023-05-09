package today.creame.web.coin.application;

import today.creame.web.coin.application.model.CoinsHistoryResult;
import today.creame.web.coin.application.model.CoinsUpdateParameter;
import today.creame.web.coin.application.model.MyCoinStatResult;

import java.util.List;

public interface CoinsService {

    void update(CoinsUpdateParameter parameter);

    MyCoinStatResult getCoinStatByMember(Long id);

    List<CoinsHistoryResult> history(Long id, Integer since);
}
