package today.creame.web.influence.entrypoint.rest.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.applicaton.model.ReviewKindStatResult;
import today.creame.web.matching.domain.ReviewKinds;

@Getter
@ToString
public class InfluenceReviewStatResponse {
    private Map<String, Integer> stat = new HashMap<>();

    public InfluenceReviewStatResponse(List<ReviewKindStatResult> results) {
        for (ReviewKinds kinds : ReviewKinds.values()) {
            stat.put(kinds.name(), 0);
        }
        results.stream().forEach(item -> stat.put(item.getKinds().name(), item.getTotal()));
    }
}
