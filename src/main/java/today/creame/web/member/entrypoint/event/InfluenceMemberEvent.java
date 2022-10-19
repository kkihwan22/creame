package today.creame.web.member.entrypoint.event;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.member.entrypoint.event.model.InfluenceMemberCreatEvent;

@RequiredArgsConstructor
@Component
public class InfluenceMemberEvent {
    private final Logger log = LoggerFactory.getLogger(InfluenceMemberEvent.class);

    @EventListener
    public void listen(InfluenceMemberCreatEvent event) {

    }
}
