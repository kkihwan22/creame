package today.creame.web.coin.entrypoint.event;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.coin.application.CoinsService;
import today.creame.web.coin.application.model.CoinsUpdateParameter;
import today.creame.web.coin.domain.CoinsHistoryType;
import today.creame.web.share.event.MatchingEndEvent;

@RequiredArgsConstructor
@Component
public class CoinHistoryEventHandler {

    private final Logger log = LoggerFactory.getLogger(CoinHistoryEventHandler.class);
    private final CoinsService coinsService;

    @EventListener
    private void handle(MatchingEndEvent event) {
        log.debug("coin history handler. event: {}", event);
        coinsService.update(new CoinsUpdateParameter(event.getMemberId(), event.getUsedCoin(), CoinsHistoryType.USING));
    }
}
