package today.creame.web.member.social.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.member.social.feign.io.google.GoogleTokenInfoRequest;
import today.creame.web.member.social.feign.io.google.GoogleTokenInfoResponse;
import today.creame.web.member.social.feign.io.google.GoogleTokenRequest;
import today.creame.web.member.social.feign.io.google.GoogleTokenResponse;

@FeignClient(
        name = "GoogleClient",
        url = "${google.base_url}"
)
public interface GoogleClient {
    @PostMapping("/token")
    ResponseEntity<GoogleTokenResponse> getToken(@RequestBody GoogleTokenRequest request);

    @PostMapping("/tokeninfo")
    ResponseEntity<GoogleTokenInfoResponse> getProfile(@RequestBody GoogleTokenInfoRequest request);
}
