package today.creame.web.influence.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.ME;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.model.BaseParameter;

@NoArgsConstructor
@Getter
@ToString
public class InfluenceGreetingsQueryParameter implements BaseParameter {

    @PermitRule(type = ME)
    private Long influenceId;

    public InfluenceGreetingsQueryParameter(Long influenceId) {
        this.influenceId = influenceId;
    }

    @Override
    public Object toEntity() {
        return null;
    }
}
