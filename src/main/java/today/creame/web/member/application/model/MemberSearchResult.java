package today.creame.web.member.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;

@Getter
@ToString
public class MemberSearchResult {
    private Long id;
    private String email;
    private String phoneNumber;
    private String nickname;

    public MemberSearchResult(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
        this.nickname = member.getNickname();
    }
}