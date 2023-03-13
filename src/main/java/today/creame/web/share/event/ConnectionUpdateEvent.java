package today.creame.web.share.event;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.domain.OnOffCondition;

@Getter
@ToString
public class ConnectionUpdateEvent {
    private Long influenceId;
    private String cid;
    private OnOffCondition onOffCondition;

    public ConnectionUpdateEvent(Long influenceId, String cid, OnOffCondition onOffCondition) {
        this.influenceId = influenceId;
        this.cid = cid;
        this.onOffCondition = onOffCondition;
    }
}
