package today.creame.web.share.event;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.MatchingProgressStatus;
import today.creame.web.matching.domain.PaidType;

@Getter
@ToString
public class MatchingEvent {
    private String uid;
    private String cid;
    private String callId;
    private MatchingProgressStatus matchingProgressStatus;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean deferred;
    private Integer usedCoins;
    private PaidType paidType;

    public MatchingEvent(String uid, String cid, String callId, MatchingProgressStatus matchingProgressStatus, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean deferred, Integer usedCoins, PaidType paidType) {
        this.uid = uid;
        this.cid = cid;
        this.callId = callId;
        this.matchingProgressStatus = matchingProgressStatus;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.deferred = deferred;
        this.usedCoins = usedCoins;
        this.paidType = paidType;
    }
}
