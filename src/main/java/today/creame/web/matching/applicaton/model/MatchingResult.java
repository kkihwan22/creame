package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingProgressStatus;
import today.creame.web.matching.domain.PaidType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<Category> categories;

    private PaidType paidType;

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
        this.categories = matching.getInfluence().getCategories().stream().map(InfluenceCategory::getCategory).collect(Collectors.toList());
        this.paidType = matching.getPaidType();
    }
}