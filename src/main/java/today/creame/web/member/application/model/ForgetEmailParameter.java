package today.creame.web.member.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.TOKEN;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.model.BaseParameter;

@Getter
@ToString
public class ForgetEmailParameter implements BaseParameter {
    @PermitRule(type = TOKEN)
    private Long token;
    private String phoneNumber;

    public ForgetEmailParameter(Long token, String phoneNumber) {
        this.token = token;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Object toEntity() {
        return null;
    }
}
