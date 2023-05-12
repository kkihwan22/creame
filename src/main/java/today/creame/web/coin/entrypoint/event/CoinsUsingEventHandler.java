package today.creame.web.coin.entrypoint.event;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.coin.application.CoinsService;
import today.creame.web.coin.application.model.CoinsUpdateParameter;
import today.creame.web.coin.domain.CoinsHistoryType;
import today.creame.web.share.event.CoinUsingEvent;
import today.creame.web.share.event.PaymentEvent;

@RequiredArgsConstructor
@Component
public class CoinsUsingEventHandler {
    private final Logger log = LoggerFactory.getLogger(CoinsUsingEventHandler.class);
    private final CoinsService coinsService;

    @EventListener
    public void handle(CoinUsingEvent event) {
        log.debug("event - {}", event);
        coinsService.update(new CoinsUpdateParameter(event.getMemberId(), event.getCoins(), CoinsHistoryType.USING));
    }
}