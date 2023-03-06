package today.creame.web.share.event;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.MatchingProgressStatus;

@Getter
@ToString
public class MatchingEvent {
    private String uid;
    private String cid;
    private MatchingProgressStatus matchingProgressStatus;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean deferred;
    private Integer usedCoins;

    public MatchingEvent(String uid, String cid, MatchingProgressStatus matchingProgressStatus, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean deferred, Integer usedCoins) {
        this.uid = uid;
        this.cid = cid;
        this.matchingProgressStatus = matchingProgressStatus;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.deferred = deferred;
        this.usedCoins = usedCoins;
    }
}
