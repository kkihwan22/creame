package today.creame.web.member.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberNicknameUpdateRequest {

    @NotBlank
    private String nickname;
}
