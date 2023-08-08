package today.creame.web.ranking.application.model;

import lombok.Getter;
import today.creame.web.ranking.domain.Ranking;

@Getter
public class RankingResult {
    private Long id;
    private int level;
    private String description;

    public RankingResult(Ranking ranking) {
        this.id = ranking.getId();
        this.level = ranking.getLevel();
        this.description = ranking.getDescription();
    }
}
