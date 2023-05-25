package today.creame.web.member.social.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.member.social.feign.io.TokenRequest;
import today.creame.web.member.social.feign.io.apple.AppleTokenResponse;

@FeignClient(
        name = "appleClient",
        url = "${apple.base_url}"
)
public interface AppleClient {
    @PostMapping(value = "/auth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<AppleTokenResponse> getToken(@RequestBody TokenRequest request);

}