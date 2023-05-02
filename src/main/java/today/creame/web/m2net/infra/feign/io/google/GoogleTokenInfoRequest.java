package today.creame.web.m2net.infra.feign.io.google;

import lombok.Getter;

@Getter
public class GoogleTokenInfoRequest {
    private String idToken;

    public GoogleTokenInfoRequest(String idToken) {
        this.idToken = idToken;
    }
}
