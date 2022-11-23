package today.creame.web.member.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.ME;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;

@Getter
@ToString
public class MemberExpireParameter {
    @PermitRule(type = ME)
    private Long id;
}
