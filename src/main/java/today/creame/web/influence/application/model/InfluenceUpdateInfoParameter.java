package today.creame.web.influence.application.model;

import lombok.Getter;
import today.creame.web.influence.domain.Rank;
import today.creame.web.influence.entrypoint.rest.io.InfluenceUpdateInfoRequest;

import java.util.List;

@Getter
public class InfluenceUpdateInfoParameter {
    private Long id;
    private String name;
    private Rank rank;
    private List<String> categories;
    private String introduction;

    public InfluenceUpdateInfoParameter(Long id, InfluenceUpdateInfoRequest request) {
        this.id = id;
        this.name = request.getName();
        this.rank = request.getRank();
        this.categories = request.getCategories();
        this.introduction = request.getIntroduction();
    }
}
