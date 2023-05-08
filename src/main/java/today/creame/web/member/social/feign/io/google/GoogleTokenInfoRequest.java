package today.creame.web.member.social.feign.io.google;

import lombok.Getter;

@Getter
public class GoogleTokenInfoRequest {
    private String idToken;

    public GoogleTokenInfoRequest(String idToken) {
        this.idToken = idToken;
    }
}
