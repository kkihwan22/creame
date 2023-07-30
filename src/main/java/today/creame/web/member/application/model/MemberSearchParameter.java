package today.creame.web.member.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSearchParameter {
    private String email;
    private String phoneNumber;
    private String nickname;
}
