package today.creame.web.matching.applicaton.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Influence;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingProgressStatus;
import today.creame.web.matching.domain.MatchingReview;

@Getter
@ToString
public class MatchingHistoryResult {
    private Long id;
    private Long influenceId;
    private String extensionNumber;
    private String nickname;
    private String profileImage;
    private LocalDateTime matchingDateTime;
    private MatchingProgressStatus status;

    public MatchingHistoryResult(Matching matching, String profileImage) {
        this.id = matching.getId();
        this.influenceId = matching.getInfluence().getId();
        this.extensionNumber = matching.getInfluence().getExtensionNumber();
        this.nickname = matching.getInfluence().getNickname();
        this.matchingDateTime = matching.getCreatedDateTime();
        this.status = matching.getStatus();
        this.profileImage = profileImage;
    }

    public MatchingHistoryResult(Matching matching) {
        this.id = matching.getId();
        Influence influence = matching.getInfluence();
        this.influenceId = influence.getId();
        this.extensionNumber = influence.getExtensionNumber();
        this.nickname = influence.getNickname();
        this.matchingDateTime = matching.getCreatedDateTime();
        this.status = matching.getStatus();

        if (influence.getProfileImages().size() > 0) {
            this.profileImage = influence.getProfileImages().get(0).getFileResourceUri();
        }
    }
}