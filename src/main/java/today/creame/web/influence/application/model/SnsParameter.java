package today.creame.web.influence.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.ME;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.SNS;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.model.BaseParameter;

@Getter
@ToString
public class SnsParameter implements BaseParameter {
    @PermitRule(type = ME)
    private Long id;
    private SNS sns;

    public SnsParameter(Long id, SNS sns) {
        this.id = id;
        this.sns = sns;
    }

    @Override
    public Object toEntity() {
        return null;
    }
}
