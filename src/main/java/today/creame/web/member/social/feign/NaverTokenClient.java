package today.creame.web.member.social.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import today.creame.web.member.social.feign.io.naver.NaverTokenResponse;

@FeignClient(
        name = "NaverTokenClient",
        url = "https://nid.naver.com/oauth2.0"
)
public interface NaverTokenClient {

    @GetMapping("/token")
    ResponseEntity<NaverTokenResponse> getToken(@RequestParam("grant_type") String grantType,
                                                @RequestParam("client_id") String clientId,
                                                @RequestParam("client_secret") String clientSecret,
                                                @RequestParam("redirect_uri") String redirectUri,
                                                @RequestParam("code") String code,
                                                @RequestParam("state") String state
                                                );
}
