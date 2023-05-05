package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingProgressStatus;

import java.time.LocalDateTime;

@Getter @ToString
public class MatchingResult {
    private Long id;
    private Long influenceId;
    private String extensionNumber;
    private String nickname;
    private String profileImage;
    private LocalDateTime matchingDateTime;
    private MatchingProgressStatus status;

    private LocalDateTime start;
    private LocalDateTime end;
    private int usedCoins;
    private String category;

    public MatchingResult(Matching matching) {
        this.id = matching.getId();
        this.influenceId = matching.getInfluence().getId();
        this.extensionNumber = matching.getInfluence().getExtensionNumber();
        this.nickname = matching.getInfluence().getNickname();
        this.profileImage = matching.getInfluence().getProfileImages().get(0).getFileResourceUri();
        this.matchingDateTime = matching.getCreatedDateTime();
        this.status = matching.getStatus();
        this.start = matching.getStartDateTime();
        this.end = matching.getEndedDateTime();
        this.usedCoins = matching.getUsedCoins();
    }
}