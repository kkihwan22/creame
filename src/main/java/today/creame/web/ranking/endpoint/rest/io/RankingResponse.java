package today.creame.web.ranking.endpoint.rest.io;

import lombok.Getter;
import today.creame.web.ranking.application.model.RankingResult;

@Getter
public class RankingResponse {
    private Long id;
    private int level;
    private String description;

    public RankingResponse(RankingResult result) {
        this.id = result.getId();
        this.level = result.getLevel();
        this.description = result.getDescription();
    }
}
