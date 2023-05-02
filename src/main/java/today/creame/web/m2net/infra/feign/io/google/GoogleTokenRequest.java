package today.creame.web.m2net.infra.feign.io.google;

import lombok.Getter;

@Getter
public class GoogleTokenRequest {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String grantType;

    public GoogleTokenRequest(String clientId, String clientSecret, String code, String redirectUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
        this.redirectUri = redirectUri;
        this.grantType = "authorization_code";
    }
}
