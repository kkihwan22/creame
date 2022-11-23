package today.creame.web.member.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.TOKEN;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;

@Getter
@ToString
public class PasswordParameter {

    private String email;
    private String phoneNumber;

    @PermitRule(type = TOKEN)
    private Long token;
}
