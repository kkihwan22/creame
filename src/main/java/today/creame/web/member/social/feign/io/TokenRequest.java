package today.creame.web.member.social.feign.io;

import lombok.Getter;

@Getter
public class TokenRequest {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String grant_type;
    private String state;

    public TokenRequest(String clientId, String clientSecret, String code, String state, String redirectUri) {
        this.client_id = clientId;
        this.client_secret = clientSecret;
        this.code = code;
        this.redirect_uri = redirectUri;
        this.state = state;
        this.grant_type = "authorization_code";
    }
}
