package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.matching.applicaton.model.ReviewCreateParameter;
import today.creame.web.matching.domain.ReviewKinds;
import today.creame.web.share.entrypoint.validator.EnumConstraint;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @ToString
public class ReviewCreateRequest {
    @Min(1)
    @Max(5)
    private int rate;

    @EnumConstraint(enumClass = Category.class, ignoreCase = true)
    private Category category;

    @EnumConstraint(enumClass = ReviewKinds.class, ignoreCase = true)
    private ReviewKinds reviewKinds;

    @NotBlank
    private String content;

    public ReviewCreateParameter withMatchingId(Long id) {
        return new ReviewCreateParameter(
                id, this.rate, this.category, this.reviewKinds, this.content
        );
    }
}
