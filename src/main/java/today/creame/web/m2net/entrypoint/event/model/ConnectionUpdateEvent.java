package today.creame.web.m2net.entrypoint.event.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.domain.OnOffCondition;

@Getter
@ToString
public class ConnectionUpdateEvent {
    private Long influenceId;
    private OnOffCondition onOffCondition;

    public ConnectionUpdateEvent(Long influenceId, OnOffCondition onOffCondition) {
        this.influenceId = influenceId;
        this.onOffCondition = onOffCondition;
    }
}
