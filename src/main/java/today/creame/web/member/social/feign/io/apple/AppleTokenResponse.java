package today.creame.web.member.social.feign.io.apple;

import lombok.Getter;

@Getter
public class AppleTokenResponse {
    private String id_token;
    private String access_token;
    private String error;
    private String state;
}
