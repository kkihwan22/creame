package today.creame.web.member.social.provider.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ProviderProfileResult {

    private String email;
    private String nickname;

    public ProviderProfileResult(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
