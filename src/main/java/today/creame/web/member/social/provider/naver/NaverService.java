package today.creame.web.member.social.provider.naver;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import today.creame.web.member.social.feign.NaverProfileClient;
import today.creame.web.member.social.feign.NaverTokenClient;
import today.creame.web.member.social.feign.io.TokenRequest;
import today.creame.web.member.social.feign.io.naver.NaverTokenInfoResponse;
import today.creame.web.member.social.feign.io.naver.NaverTokenResponse;
import today.creame.web.member.social.provider.SocialProviderService;
import today.creame.web.member.social.provider.io.ProviderProfileResult;

import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class NaverService implements SocialProviderService {
    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverSecret;

    @Value("${naver.redirect_uri}")
    private String naverRedirectUri;

    private final NaverTokenClient naverTokenClient;
    private final NaverProfileClient naverProfileClient;

    @Override
    public String generateUrl() {
        StringBuilder sb = new StringBuilder("https://nid.naver.com/oauth2.0/authorize?");
        sb.append("client_id=" + naverClientId);
        sb.append("&redirect_uri=" + naverRedirectUri);
        sb.append("&response_type=code");

        return sb.toString();
    }

    @Override
    public String getToken(String code, String state) {
        TokenRequest request = new TokenRequest(naverClientId, naverSecret, code, state, naverRedirectUri);
        ResponseEntity<NaverTokenResponse> naverTokenResponseResponseEntity = naverTokenClient.getToken("authorization_code", naverClientId, naverSecret, naverRedirectUri, code, "");
        if(OK.equals(naverTokenResponseResponseEntity.getStatusCode()) && Objects.isNull(naverTokenResponseResponseEntity.getBody().getError())) {
            return naverTokenResponseResponseEntity.getBody().getAccess_token();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public ProviderProfileResult getInfo(String token) {
        String authorization = "Bearer " + token;
        ResponseEntity<NaverTokenInfoResponse> naverTokenInfoResponse = naverProfileClient.getProfile(authorization);
        if(OK.equals(naverTokenInfoResponse.getStatusCode())) {
            return naverTokenInfoResponse.getBody().getResponse().toResult();
        }
        return null;
    }
}
