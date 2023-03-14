package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.aspect.permit.PermitRuleType;
import today.creame.web.share.model.BaseParameter;

@Getter
@ToString
public class InfluenceItemParameter implements BaseParameter {

    @PermitRule(type = PermitRuleType.ME)
    private Long influenceId;
    private Integer index;

    public InfluenceItemParameter(Long influenceId, Integer index) {
        this.influenceId = influenceId;
        this.index = index;
    }

    public InfluenceItemParameter(Long influenceId) {
        this(influenceId, null);
    }

    @Override
    public Object toEntity() {
        return null;
    }
}
