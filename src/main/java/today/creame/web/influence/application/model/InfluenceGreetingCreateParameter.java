package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceGreetingsHistory;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.aspect.permit.PermitRuleType;
import today.creame.web.share.model.BaseParameter;

@NoArgsConstructor
@Getter
@ToString
public class InfluenceGreetingCreateParameter implements BaseParameter {
    @PermitRule(type = PermitRuleType.ME) // TODO: 리스트로 변경
    private Long influenceId;
    private Long fileId;
    private String fileUri;

    public InfluenceGreetingCreateParameter(Long influenceId, Long fileId, String fileUri) {
        this.influenceId = influenceId;
        this.fileId = fileId;
        this.fileUri = fileUri;
    }

    public InfluenceGreetingsHistory toEntity() {
        return new InfluenceGreetingsHistory(this.influenceId, this.fileId, this.fileUri);
    }
}