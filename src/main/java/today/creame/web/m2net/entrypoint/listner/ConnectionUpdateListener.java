package today.creame.web.m2net.entrypoint.listner;

import static today.creame.web.share.domain.OnOffCondition.ON;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.m2net.application.M2netCounselorService;
import today.creame.web.share.event.ConnectionUpdateEvent;

@RequiredArgsConstructor
@Component
public class ConnectionUpdateListener {
    private final Logger log = LoggerFactory.getLogger(ConnectionUpdateListener.class);
    private final M2netCounselorService m2netCounselorService;

    @EventListener // TODO: async
    public void handle(ConnectionUpdateEvent event) {
        if (ON == event.getOnOffCondition()) m2netCounselorService.on(event.getCid());
        else m2netCounselorService.off(event.getCid());
    }
}
