package today.creame.web.m2net.entrypoint.listner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import today.creame.web.m2net.application.M2netUserService;
import today.creame.web.share.event.AutoChargingConfigEvent;

@RequiredArgsConstructor
@Component
public class AutoChargingConfigListener {
    private final M2netUserService m2netUserService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handle(AutoChargingConfigEvent event) {
        m2netUserService.updateAutoChargingConfig(event);
    }
}
