package today.creame.web.member.social.feign.io.naver;

import lombok.Getter;
import today.creame.web.member.social.provider.io.ProviderProfileResult;

@Getter
public class NaverTokenInfoResponse {
    private String resultcode;
    private String message;
    private Response response;

    @Getter
    public static class Response {
        private String id;
        private String nickname;
        private String email;

        public ProviderProfileResult toResult() {
            return new ProviderProfileResult(this.email, this.nickname);

        }

    }
}
