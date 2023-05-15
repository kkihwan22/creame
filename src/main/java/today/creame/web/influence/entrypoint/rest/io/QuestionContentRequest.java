package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.application.model.InfluenceQuestionParameter;

@Getter @ToString
public class QuestionContentRequest {

    @NotBlank(message = "내용을 등록해주세요.")
    private String content;

    public InfluenceQuestionParameter toParameter(Long influenceId, Long questionerId) {
        return new InfluenceQuestionParameter(influenceId, questionerId, this.content);
    }
}
