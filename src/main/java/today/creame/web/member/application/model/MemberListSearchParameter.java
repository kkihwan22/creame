package today.creame.web.member.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.creame.web.member.domain.MemberStatus;
import today.creame.web.member.entrypoint.rest.io.MemberListSearchRequest;

@Getter
@AllArgsConstructor
public class MemberListSearchParameter {
    private String nickname;
    private MemberStatus status;

    public MemberListSearchParameter(MemberListSearchRequest request) {
        this.nickname = request.getNickname();
        this.status = request.getStatus();
    }
}
