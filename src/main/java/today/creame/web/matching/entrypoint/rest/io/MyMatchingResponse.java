package today.creame.web.matching.entrypoint.rest.io;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MyMatchingResponse {
    private Long matchingId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer usedCoins;
    private Long influenceId;
    private String extensionNumber;
    private String nickname;
    private String profileImageUrl;

    public MyMatchingResponse(Long matchingId, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer usedCoins, Long influenceId, String extensionNumber, String nickname, String profileImageUrl) {
        this.matchingId = matchingId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.usedCoins = usedCoins;
        this.influenceId = influenceId;
        this.extensionNumber = extensionNumber;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
