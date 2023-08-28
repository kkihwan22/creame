package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingProgressStatus;
import today.creame.web.matching.domain.PaidType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class MatchingListResponse {
    private Long id;
    private String influenceNickname;
    private String memberNickname;
    private MatchingProgressStatus status;
    private PaidType paidType;
    private String callTime;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private Integer usedCoins;
    private boolean deferred;

    public MatchingListResponse(Matching matching) {
        if(Objects.nonNull(matching.getStartDateTime()) && Objects.nonNull(matching.getEndedDateTime())) {
            Duration duration = Duration.between(matching.getStartDateTime(), matching.getEndedDateTime());
            long hours = duration.toHours();
            long minutes = (duration.toMinutes() % 60);
            long seconds = (duration.getSeconds() % 60);
            this.callTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

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
