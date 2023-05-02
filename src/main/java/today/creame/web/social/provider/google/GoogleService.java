package today.creame.web.social.provider.google;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import today.creame.web.m2net.infra.feign.GoogleClient;
import today.creame.web.m2net.infra.feign.io.google.GoogleTokenInfoRequest;
import today.creame.web.m2net.infra.feign.io.google.GoogleTokenInfoResponse;
import today.creame.web.m2net.infra.feign.io.google.GoogleTokenRequest;
import today.creame.web.m2net.infra.feign.io.google.GoogleTokenResponse;
import today.creame.web.social.provider.SocialProviderService;

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
    public String getToken(String code) {
        GoogleTokenRequest request = new GoogleTokenRequest(googleClientId, googleSecret, code, redirectUri);

        ResponseEntity<GoogleTokenResponse> googleTokenResponse = client.getToken(request);
        if(OK.equals(googleTokenResponse.getStatusCode())) {
            return googleTokenResponse.getBody().getIdToken();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String getUserEmail(String idToken) {
        GoogleTokenInfoRequest request = new GoogleTokenInfoRequest(idToken);

        ResponseEntity<GoogleTokenInfoResponse> googleTokenInfoResponse = client.getEmail(request);
        if(OK.equals(googleTokenInfoResponse.getStatusCode())) {
            return googleTokenInfoResponse.getBody().getEmail();
        }
        return StringUtils.EMPTY;
    }
}
