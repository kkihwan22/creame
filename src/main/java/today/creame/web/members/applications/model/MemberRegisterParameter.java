package today.creame.web.members.applications.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.members.domain.Member;

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