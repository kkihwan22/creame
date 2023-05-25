package today.creame.web.member.social.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import today.creame.web.member.social.feign.io.naver.NaverTokenInfoResponse;

@FeignClient(
        name = "NaverProfileClient",
        url = "${naver.profile_url}"
)
public interface NaverProfileClient {
    @GetMapping("/v1/nid/me")
    ResponseEntity<NaverTokenInfoResponse> getProfile(@RequestHeader(value = "Authorization") String authorization);
}
