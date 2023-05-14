package today.creame.web.member.social.provider.google;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import today.creame.web.member.social.feign.io.google.GoogleTokenInfoResponse;
import today.creame.web.member.social.feign.io.google.GoogleTokenResponse;
import today.creame.web.member.social.feign.GoogleClient;
import today.creame.web.member.social.feign.io.TokenInfoRequest;
import today.creame.web.member.social.feign.io.TokenRequest;
import today.creame.web.member.social.provider.SocialProviderService;
import today.creame.web.member.social.provider.io.ProviderProfileResult;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class GoogleService implements SocialProviderService {

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.client.secret}")
    private String googleSecret;

    @Value("${google.scope}")
    private String googleScope;

    @Value("${google.redirect_uri}")
    private String redirectUri;

    private final GoogleClient client;

    @Override
    public String generateUrl() {
        StringBuilder sb = new StringBuilder("https://accounts.google.com/o/oauth2/v2/auth?");
        sb.append("client_id=" + googleClientId);
        sb.append("&redirect_uri=" + redirectUri);
        sb.append("&response_type=code");
        sb.append("&scope=" + googleScope);

        return sb.toString();
    }

    @Override
    public String getToken(String code, String state) {
        TokenRequest request = new TokenRequest(googleClientId, googleSecret, code, state,redirectUri);
        ResponseEntity<GoogleTokenResponse> googleTokenResponse = client.getToken(request);
        if(OK.equals(googleTokenResponse.getStatusCode())) {
            return googleTokenResponse.getBody().getIdToken();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public ProviderProfileResult getInfo(String token) {
        TokenInfoRequest request = new TokenInfoRequest(token);
        ResponseEntity<GoogleTokenInfoResponse> googleTokenInfoResponse = client.getProfile(request);
        if(OK.equals(googleTokenInfoResponse.getStatusCode())) {
            return googleTokenInfoResponse.getBody().toResult();
        }
        return null;
    }
}
