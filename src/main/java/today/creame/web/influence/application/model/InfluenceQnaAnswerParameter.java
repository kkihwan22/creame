package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class InfluenceQnaAnswerParameter {

    private Long id;
    private Long qnaId;
    private String content;

    public InfluenceQnaAnswerParameter(Long id, Long qnaId, String content) {
        this.id = id;
        this.qnaId = qnaId;
        this.content = content;
    }
}
