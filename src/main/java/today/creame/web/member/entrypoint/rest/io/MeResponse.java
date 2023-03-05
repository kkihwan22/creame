package today.creame.web.member.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.domain.MemberRoleCode;

@Getter
@ToString
public class MeResponse {
    private Long memberId;
    private String nickname;
    private String email;
    private String phoneNumber;
    private boolean isInfluence;

    public MeResponse(MeResult meResult) {
        this.memberId = meResult.getMemberId();
        this.nickname = meResult.getNickname();
        this.email = meResult.getEmail();
        this.phoneNumber = meResult.getPhoneNumber();
        this.isInfluence = meResult.getMemberRoleList()
            .stream()
            .anyMatch(role -> role == MemberRoleCode.INFLUENCE);
    }
}