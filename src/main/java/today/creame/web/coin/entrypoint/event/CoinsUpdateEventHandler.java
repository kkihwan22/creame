package today.creame.web.coin.entrypoint.event;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.coin.application.CoinsService;
import today.creame.web.coin.application.model.CoinsUpdateParameter;
import today.creame.web.share.event.PaymentEvent;

@RequiredArgsConstructor
@Component
public class CoinsUpdateEventHandler {
    private final Logger log = LoggerFactory.getLogger(CoinsUpdateEventHandler.class);
    private final CoinsService coinsService;

    @EventListener
    public void handle(PaymentEvent event) {
        log.debug("event - {}", event);
        coinsService.update(new CoinsUpdateParameter(event.getMemberId(), event.getCoins(), event.getType()));
    }
}