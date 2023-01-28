package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InfluenceQnaAnswerQueryParameter {

    private Long id;
    private Long qnaId;
    private String content;

    public InfluenceQnaAnswerQueryParameter(Long id, Long qnaId, String content) {
        this.id = id;
        this.qnaId = qnaId;
        this.content = content;
    }
}
