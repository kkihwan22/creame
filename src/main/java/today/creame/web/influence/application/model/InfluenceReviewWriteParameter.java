package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.InfluenceReviewKinds;

@Getter @ToString
public class InfluenceReviewWriteParameter {

    private Long influenceId;
    private int rate;
    private Category category;
    private InfluenceReviewKinds reviewKinds;
    private String content;
}
