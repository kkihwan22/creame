package today.creame.web.member.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.TOKEN;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.model.BaseParameter;

@Getter
@ToString
public class ForgetPasswordParameter implements BaseParameter {
    private String email;

    private String phoneNumber;

    @PermitRule(type = TOKEN)
    private Long token;

    public ForgetPasswordParameter(String email, String phoneNumber, Long token) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.token = token;
    }

    @Override
    public Object toEntity() {
        return null;
    }
}
