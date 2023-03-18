package today.creame.web.member.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;

@Getter
@ToString
public class MemberResult {
    private Long id;
    private String phoneNumber;
    private String password;

    public MemberResult(Member member) {
        this.id = member.getId();
        this.phoneNumber = member.getPhoneNumber();
        this.password = member.getPassword();
    }
}