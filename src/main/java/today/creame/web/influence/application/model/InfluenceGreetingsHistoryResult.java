package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.influence.domain.GreetingsProgressStatus;
import today.creame.web.influence.domain.InfluenceGreetingsHistory;

@NoArgsConstructor
@Getter
@ToString
public class InfluenceGreetingsHistoryResult {
    private Long id;
    private Long influenceId;
    private Long fileId;
    private String fileUri;
    private GreetingsProgressStatus status;

    public InfluenceGreetingsHistoryResult(Long id, Long influenceId, Long fileId, String fileUri, GreetingsProgressStatus status) {
        this.id = id;
        this.influenceId = influenceId;
        this.fileId = fileId;
        this.fileUri = fileUri;
        this.status = status;
    }

    public InfluenceGreetingsHistoryResult(InfluenceGreetingsHistory entity) {
        this.id = entity.getId();
        this.influenceId = entity.getInfluence().getId();
        this.fileId = entity.getFileId();
        this.fileUri = entity.getFileUri();
        this.status = entity.getStatus();
    }
}
