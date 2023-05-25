package today.creame.web.member.social.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import today.creame.web.member.social.feign.io.kakao.KaKaoTokenInfoResponse;

@FeignClient(
        name = "KakaoProfileClient",
        url = "${kakao.profile_url}"
)
public interface KakaoProfileClient {
    @PostMapping(value = "/v2/user/me", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<KaKaoTokenInfoResponse> getProfile(@RequestHeader(value = "Authorization") String accessToken);
}
