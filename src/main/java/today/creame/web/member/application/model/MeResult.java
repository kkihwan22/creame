package today.creame.web.member.application.model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.MemberRoleCode;

@Getter @ToString
public class MeResult {
    private Long memberId;
    private String nickname;
    private List<MemberRoleCode> memberRoleList;

    public MeResult(Long memberId, String nickname, List<MemberRoleCode> memberRoleList) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.memberRoleList = memberRoleList;
    }
}
