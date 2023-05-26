package today.creame.web.member.social.feign.io.kakao;

import lombok.Getter;

@Getter
public class KakaoTokenResponse {
    private String tokenType;
    private String access_token;
    private String refresh_token;
    private String idToken;
}
