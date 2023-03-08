package today.creame.web.member.application.model;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberRole;
import today.creame.web.member.domain.MemberRoleCode;

@Getter
@ToString
public class MeResult {
    private Long memberId;
    private String uId;
    private String nickname;
    private String email;
    private String phoneNumber;
    private List<MemberRoleCode> memberRoleList;

    public MeResult(Member member) {
        this.memberId = member.getId();
        this.uId = member.getM2netUserId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
        this.memberRoleList = member.getRoles()
            .stream().map(MemberRole::getCodeName)
            .collect(Collectors.toList());
    }
}
