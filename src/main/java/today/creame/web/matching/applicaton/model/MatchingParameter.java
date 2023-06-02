package today.creame.web.matching.applicaton.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.MatchingProgressStatus;
import today.creame.web.matching.domain.PaidType;

@Getter
@ToString
public class MatchingParameter {
    private String uid; // TODO: UK 걸어야지
    private String cid; // TODO: UK 걸어야지
    private MatchingProgressStatus matchingProgressStatus;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean deferred;
    private Integer usedCoins;
    private PaidType paidType;

    public MatchingParameter(String uid, String cid, MatchingProgressStatus matchingProgressStatus, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean deferred, Integer usedCoins, PaidType paidType) {
        this.uid = uid;
        this.cid = cid;
        this.matchingProgressStatus = matchingProgressStatus;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.deferred = deferred;
        this.usedCoins = usedCoins;
        this.paidType = paidType;
    }
}