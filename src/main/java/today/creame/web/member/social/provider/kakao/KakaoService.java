package today.creame.web.member.social.provider.kakao;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import today.creame.web.member.social.feign.KakaoProfileClient;
import today.creame.web.member.social.feign.KakaoTokenClient;
import today.creame.web.member.social.feign.io.TokenRequest;
import today.creame.web.member.social.feign.io.kakao.KaKaoTokenInfoResponse;
import today.creame.web.member.social.feign.io.kakao.KakaoTokenResponse;
import today.creame.web.member.social.provider.SocialProviderService;
import today.creame.web.member.social.provider.io.ProviderProfileResult;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class KakaoService implements SocialProviderService {

    @Value("${kakao.client.id}")
    private String kakaoClientId;

    @Value("${kakao.client.secret}")
    private String kakaoSecret;

    @Value("${kakao.scope}")
    private String kakaoScope;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    @Value("${kakao.oauth_url}")
    private String kakaoBaseUrl;
    private final KakaoTokenClient tokenClient;
    private final KakaoProfileClient profileClient;

    @Override
    public String generateUrl() {
        StringBuilder sb = new StringBuilder(kakaoBaseUrl + "/oauth/authorize");
        sb.append("?client_id=" + kakaoClientId);
        sb.append("&redirect_uri=" + redirectUri);
        sb.append("&response_type=code");
        sb.append("&scope=" + kakaoScope);

        return sb.toString();
    }

    @Override
    public String getToken(String code, String state) {
        TokenRequest request = new TokenRequest(kakaoClientId, kakaoSecret, code, state,redirectUri);
        ResponseEntity<KakaoTokenResponse> kakaoTokenResponse = tokenClient.getToken(request);
        if(OK.equals(kakaoTokenResponse.getStatusCode())) {
            return kakaoTokenResponse.getBody().getAccess_token();
        }

        return StringUtils.EMPTY;
    }

    @Override
    public ProviderProfileResult getInfo(String token) {
        String accessToken = "Bearer " + token;
        ResponseEntity<KaKaoTokenInfoResponse> kakaoTokenInfoResponse = profileClient.getProfile(accessToken);
        if(OK.equals(kakaoTokenInfoResponse.getStatusCode())) {
            return kakaoTokenInfoResponse.getBody().toResult();
        }

        return null;
    }
}
