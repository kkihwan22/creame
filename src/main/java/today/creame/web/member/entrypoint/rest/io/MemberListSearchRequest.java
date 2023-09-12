package today.creame.web.member.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.creame.web.member.domain.MemberStatus;

@Getter
@AllArgsConstructor
public class MemberListSearchRequest {
    private String nickname;
    private MemberStatus status;
}
