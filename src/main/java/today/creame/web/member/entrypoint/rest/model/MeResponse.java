package today.creame.web.member.entrypoint.rest.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.domain.MemberRole;
import today.creame.web.member.domain.MemberRoleCode;

@Getter @ToString
public class MeResponse {
    private Long memberId;
    private String nickname;
    private boolean isInfluence;

    public MeResponse(MeResult meResult) {
        this.memberId = meResult.getMemberId();
        this.nickname = meResult.getNickname();
        this.isInfluence = meResult.getMemberRoleList()
            .stream()
            .anyMatch(role -> role == MemberRoleCode.INFLUENCE);
    }
}