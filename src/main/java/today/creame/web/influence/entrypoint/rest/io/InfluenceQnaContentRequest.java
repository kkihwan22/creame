package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.application.model.InfluenceQnaQuestionParameter;

@Getter
@ToString
public class InfluenceQnaContentRequest {

    @NotBlank(message = "내용을 등록해주세요.")
    private String content;

    public InfluenceQnaQuestionParameter toParameter(Long influenceId, Long questionerId) {
        return new InfluenceQnaQuestionParameter(influenceId, questionerId, this.content);
    }
}
