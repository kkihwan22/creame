package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingProgressStatus;
import today.creame.web.matching.domain.PaidType;

import java.time.LocalDateTime;

@Getter
public class MatchingListResponse {
    private Long id;
    private String influenceNickname;
    private String memberNickname;
    private MatchingProgressStatus status;
    private PaidType paidType;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private Integer usedCoins;
    private boolean deferred;

    public MatchingListResponse(Matching matching) {
        this.id = matching.getId();
        this.influenceNickname = matching.getInfluence().getNickname();
        this.memberNickname = matching.getMember().getNickname();
        this.status = matching.getStatus();
        this.paidType = matching.getPaidType();
        this.startDt = matching.getStartDateTime();
        this.endDt = matching.getEndedDateTime();
        this.usedCoins = matching.getUsedCoins();
        this.deferred = matching.isDeferred();
    }
}
