package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class InfluenceQnaAnswerParameter {

    private Long id;
    private Long answererId;
    private String content;

    public InfluenceQnaAnswerParameter(Long id, Long answererId, String content) {
        this.id = id;
        this.answererId = answererId;
        this.content = content;
    }
}
