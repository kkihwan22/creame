package today.creame.web.member.social.feign.io;

import lombok.Getter;

@Getter
public class TokenInfoRequest {
    private String idToken;

    public TokenInfoRequest(String idToken) {
        this.idToken = idToken;
    }
}
