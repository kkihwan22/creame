package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceQna;
import today.creame.web.share.model.BaseParameter;

@Getter @ToString
public class InfluenceQnaAskParameter implements BaseParameter<InfluenceQna> {

    private Long influenceId;
    private Long questionerId;
    private String content;

    public InfluenceQnaAskParameter(Long influenceId, Long questionerId, String content) {
        this.influenceId = influenceId;
        this.questionerId = questionerId;
        this.content = content;
    }

    @Override
    public InfluenceQna toEntity() {
        return new InfluenceQna(influenceId, questionerId, content);
    }
}
