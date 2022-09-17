package today.creame.web.member.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberStatus;

@Getter @ToString
public class MemberRegisterParameter {
    private final String email;
    private final String nickname;
    private final String password;
    private final String token;
    private final String phoneNumber;

    public MemberRegisterParameter(
            final String email,
            final String nickname,
            final String password,
            final String token,
            final String phoneNumber) {

        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.token = token;
        this.phoneNumber = phoneNumber;
    }

    public Member toMember() {
        return new Member(null, email, password, nickname, phoneNumber, MemberStatus.ACTIVE);
    }
}