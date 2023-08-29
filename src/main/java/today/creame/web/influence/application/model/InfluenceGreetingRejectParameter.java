package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.aspect.permit.PermitRuleType;

@Getter
@ToString
public class InfluenceGreetingRejectParameter {
    @PermitRule(type = PermitRuleType.ME)
    private Long influenceId;
    private Long requestId;

    public InfluenceGreetingRejectParameter(Long influenceId, Long requestId) {
        this.influenceId = influenceId;
        this.requestId = requestId;
    }
}