package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.influence.domain.GreetingsProgressStatus;
import today.creame.web.influence.domain.InfluenceGreetingsHistory;

import java.time.LocalDateTime;

@Getter
public class InfluenceGreetingResponse {
    private Long id;
    private Long InfluenceId;
    private String nickname;
    private String fileUri;
    private GreetingsProgressStatus status;
    private String reason;
    private LocalDateTime createdDt;
    private Long updatedBy;
    private String updatedByNickname;
    private LocalDateTime updatedDt;


    public InfluenceGreetingResponse(InfluenceGreetingsHistory influenceGreetingsHistory, String updatedNickname) {
        this.id = influenceGreetingsHistory.getId();
        this.InfluenceId = influenceGreetingsHistory.getInfluence().getId();
        this.nickname = influenceGreetingsHistory.getInfluence().getNickname();
        this.fileUri = influenceGreetingsHistory.getFileUri();
        this.status = influenceGreetingsHistory.getStatus();
        this.reason = influenceGreetingsHistory.getReason();
        this.createdDt = influenceGreetingsHistory.getCreatedDateTime();
        this.updatedBy = influenceGreetingsHistory.getUpdatedBy();
        this.updatedByNickname = updatedNickname;
        this.updatedDt = influenceGreetingsHistory.getUpdatedDateTime();
    }
}
