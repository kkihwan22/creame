package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.matching.domain.ReviewKinds;

@Getter @ToString
public class InfluenceReviewWriteParameter {

    private Long influenceId;
    private int rate;
    private Category category;
    private ReviewKinds reviewKinds;
    private String content;
}
