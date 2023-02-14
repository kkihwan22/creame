package today.creame.web.matching.entrypoint.event;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.matching.entrypoint.event.model.MatchingCreateEvent;

@RequiredArgsConstructor
@Component
public class MatchingCreateListener {
    private final Logger log = LoggerFactory.getLogger(MatchingCreateListener.class);

    @EventListener
    public void handle(MatchingCreateEvent event) {

    }
}
