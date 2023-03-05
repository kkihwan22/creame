package today.creame.web.member.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberPasswordUpdateRequest {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String changedPassword;
}
