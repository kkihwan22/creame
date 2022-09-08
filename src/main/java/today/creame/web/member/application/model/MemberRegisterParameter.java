package today.creame.web.member.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;

@Getter @ToString
public class MemberRegisterParameter {
    private String email;
    private String nickname;
    private String password;
    private String verifiedCode;
    private String phoneNumber;

    public Member toEntity() {
        return new Member(null, email, nickname, password, phoneNumber);
    }
}