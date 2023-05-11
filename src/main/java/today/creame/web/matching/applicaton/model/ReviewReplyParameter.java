package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReviewReplyParameter {
    private Long id;
    private String content;

    public ReviewReplyParameter(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}