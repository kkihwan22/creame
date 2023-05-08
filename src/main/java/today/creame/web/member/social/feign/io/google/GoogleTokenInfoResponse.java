package today.creame.web.member.social.feign.io.google;

import lombok.Getter;
import today.creame.web.member.social.provider.io.ProviderProfileResult;

@Getter
public class GoogleTokenInfoResponse {
    private String email;
    private String nickname;

    public ProviderProfileResult toResult() {
        return new ProviderProfileResult(this.email, this.nickname);

    }
}
