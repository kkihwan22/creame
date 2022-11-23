package today.creame.web.member.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.TOKEN;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;

@Getter
@ToString
public class EmailLossParameter {
    @PermitRule(type = TOKEN)
    private Long token;
    private String phoneNumber;
}
