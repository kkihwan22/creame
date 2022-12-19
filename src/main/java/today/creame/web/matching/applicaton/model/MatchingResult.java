package today.creame.web.matching.applicaton.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingStatus;
import today.creame.web.matching.domain.TelecomCompanyCode;

@Getter
@ToString
public class MatchingResult {
    private Long matchingId;
    private Long influenceId;
    private Long memberId;
    private MatchingStatus status;
    private LocalDateTime requestDateTime;
    private LocalDateTime responseDateTime;
    private LocalDateTime endedDateTime;
    private TelecomCompanyCode telecomCompany;
    private Integer usedCoins;

    public MatchingResult(Matching matching) {
        this.matchingId = matching.getId();
        this.influenceId = matching.getInfluenceId();
        this.memberId = matching.getMemberId();
        this.status = matching.getStatus();
        this.requestDateTime = matching.getRequestDateTime();
        this.responseDateTime = matching.getResponseDateTime();
        this.endedDateTime = matching.getEndedDateTime();
        this.telecomCompany = matching.getTelecomCompany();
        this.usedCoins = matching.getUsedCoins();
    }
}