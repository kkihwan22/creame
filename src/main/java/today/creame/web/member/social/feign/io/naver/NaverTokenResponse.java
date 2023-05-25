package today.creame.web.member.social.feign.io.naver;

import lombok.Getter;

@Getter
public class NaverTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String expiresIn;
    private String error;
    private String errorDescription;
}
