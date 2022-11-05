package today.creame.web.home.application.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.InfluenceCategoryGroupByDTO;

@Getter @ToString
public class HomeInfluenceStatResult {
    private final Map<String, Long> category;

    public HomeInfluenceStatResult(List<InfluenceCategoryGroupByDTO> results) {
        category = new HashMap<>();
        Arrays.stream(Category.values()).forEach(category -> this.category.put(category.name(), 0L));
        results.stream().forEach(item -> {
            category.put(item.getCategory().name(), item.getCount());
        });

    }
}
