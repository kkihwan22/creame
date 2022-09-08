package today.creame.web.member.domain;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class Member {
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private String phoneNumber;

    public Member(Long id, String email, String nickname, String password, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
