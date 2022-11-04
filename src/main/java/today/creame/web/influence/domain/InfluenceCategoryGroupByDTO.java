package today.creame.web.influence.domain;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class InfluenceCategoryGroupByDTO {
    private Category category;
    private Long count;

    public InfluenceCategoryGroupByDTO(Category category, Long count) {
        this.category = category;
        this.count = count;
    }
}