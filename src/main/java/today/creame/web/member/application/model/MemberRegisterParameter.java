package today.creame.web.member.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.TOKEN;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberStatus;
import today.creame.web.member.domain.SignupType;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.model.BaseParameter;

@Getter
@ToString
public class MemberRegisterParameter implements BaseParameter {
    private final String email;
    private final String nickname;
    private final String password;
    private final String phoneNumber;
    @PermitRule(type = TOKEN)
    private final Long token;
    private final SignupType signupType;


    public MemberRegisterParameter(
            final String email,
            final String nickname,
            final String password,
            final String phoneNumber,
            final Long token,
            final SignupType signupType) {

        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.token = token;
        this.signupType = signupType;
    }

    @Override
    public Member toEntity() {
        return new Member(email, password, nickname, phoneNumber, MemberStatus.ACTIVE, signupType);
    }
}