package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.aspect.permit.PermitRuleType;

@Getter
@ToString
public class InfluenceGreetingApproveParameter {
    @PermitRule(type = PermitRuleType.ME) // TODO: 리스트로 변경
    private Long influenceId;
    private Long requestId;

    public InfluenceGreetingApproveParameter(Long influenceId, Long requestId) {
        this.influenceId = influenceId;
        this.requestId = requestId;
    }
}