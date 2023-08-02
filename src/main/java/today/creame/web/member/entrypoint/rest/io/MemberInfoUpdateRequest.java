package today.creame.web.member.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberInfoUpdateRequest {
    private String nickname;
    private String phoneNumber;
}
