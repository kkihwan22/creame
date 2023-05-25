package today.creame.web.member.social.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.member.social.feign.io.TokenRequest;
import today.creame.web.member.social.feign.io.kakao.KakaoTokenResponse;

@FeignClient(
        name = "KakaoTokenClient",
        url = "${kakao.token_url}"
)
public interface KakaoTokenClient {
    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<KakaoTokenResponse> getToken(@RequestBody TokenRequest request);

}
