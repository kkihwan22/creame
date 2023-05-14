package today.creame.web.member.social.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.member.social.feign.io.TokenInfoRequest;
import today.creame.web.member.social.feign.io.google.GoogleTokenInfoResponse;
import today.creame.web.member.social.feign.io.TokenRequest;
import today.creame.web.member.social.feign.io.google.GoogleTokenResponse;

@FeignClient(
        name = "GoogleClient",
        url = "${google.base_url}"
)
public interface GoogleClient {
    @PostMapping("/token")
    ResponseEntity<GoogleTokenResponse> getToken(@RequestBody TokenRequest request);

    @PostMapping("/tokeninfo")
    ResponseEntity<GoogleTokenInfoResponse> getProfile(@RequestBody TokenInfoRequest request);
}
