package today.creame.web.matching.applicaton.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingProgressStatus;

@Getter
@ToString
public class MatchingResult {
    private Long matchingId;
    private Long influenceId;
    private Long memberId;
    private MatchingProgressStatus status;
    private LocalDateTime requestDateTime;
    private LocalDateTime responseDateTime;
    private LocalDateTime endedDateTime;
    private Integer usedCoins;

    public MatchingResult(Matching matching) {

    }
}