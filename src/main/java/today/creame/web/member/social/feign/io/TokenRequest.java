package today.creame.web.member.social.feign.io;

import lombok.Getter;

@Getter
public class TokenRequest {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String grantType;
    private String state;

    public TokenRequest(String clientId, String clientSecret, String code, String state, String redirectUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
        this.redirectUri = redirectUri;
        this.state = state;
        this.grantType = "authorization_code";
    }
}
