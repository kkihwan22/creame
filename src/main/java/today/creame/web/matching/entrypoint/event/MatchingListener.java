package today.creame.web.matching.entrypoint.event;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.matching.applicaton.MatchingService;
import today.creame.web.matching.applicaton.model.MatchingParameter;
import today.creame.web.matching.domain.MatchingProgressStatus;
import today.creame.web.share.event.MatchingEvent;

@RequiredArgsConstructor
@Component
public class MatchingListener {
    private final Logger log = LoggerFactory.getLogger(MatchingListener.class);
    private final MatchingService matchingService;

    @EventListener
    public void handle(MatchingEvent event) {
        MatchingParameter parameter = of(event);
        log.info("[ Matching Event ] {}", event);
        if (parameter.getMatchingProgressStatus() == MatchingProgressStatus.START) {
            matchingService.start(parameter);
        } else {
            matchingService.end(parameter);
        }
    }

    private MatchingParameter of(MatchingEvent event) {
        return new MatchingParameter(
            event.getUid(),
            event.getCid(),
            event.getMatchingProgressStatus(),
            event.getStartDateTime(),
            event.getEndDateTime(),
            event.isDeferred(),
            event.getUsedCoins(),
                event.getPaidType()
        );
    }
}
