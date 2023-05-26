package today.creame.web.member.social.feign.io.kakao;

import lombok.Getter;
import today.creame.web.member.social.provider.io.ProviderProfileResult;

import java.util.Objects;

@Getter
public class KaKaoTokenInfoResponse {
    private KakaoAccount kakao_account;

    @Getter
    public static class KakaoAccount {
        private Profile profile;
        private String email;
    }

    @Getter
    public static class Profile {
        private String nickname;
    }

    public ProviderProfileResult toResult() {
        if(Objects.isNull(this.kakao_account) && Objects.isNull(this.kakao_account.getProfile())) {
            return null;
        }
        return new ProviderProfileResult(this.kakao_account.getEmail(), this.kakao_account.getProfile().getNickname());
    }
}
