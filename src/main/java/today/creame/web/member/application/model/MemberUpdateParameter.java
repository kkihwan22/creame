package today.creame.web.member.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.ME;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.model.BaseParameter;

@Getter
@ToString
public class MemberUpdateParameter implements BaseParameter {
    @PermitRule(type = ME)
    private Long id;

    private String password;
    private String nickname;

    public MemberUpdateParameter(Long id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }

    @Override
    public Object toEntity() {
        return null;
    }
}
