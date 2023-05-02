package today.creame.web.social.entrypoint.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;
import today.creame.web.social.application.SocialService;
import today.creame.web.social.domain.ProviderType;

import java.util.Map;


@RestController
@RequiredArgsConstructor
public class SocialRestController implements BaseRestController {

    private final SocialService socialService;

    @PostMapping("/public/v1/oauth/{provider}")
    public ResponseEntity<Body<SimpleBodyData<String>>> initSocialUrl(@PathVariable ProviderType provider) {
        String loginUrl = socialService.initSocialUrl(provider);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData(loginUrl)));
    }

    @GetMapping("/public/v1/login/oauth/{provider}")
    public ResponseEntity<Body<Map<String, String>>> socialLogin(@PathVariable ProviderType provider, @RequestParam String code) {
        Map<String, String> token = socialService.login(provider, code);
        return ResponseEntity.ok(BodyFactory.success(token));
    }
}
