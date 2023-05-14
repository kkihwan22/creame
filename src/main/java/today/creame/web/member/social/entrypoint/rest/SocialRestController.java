package today.creame.web.member.social.entrypoint.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import today.creame.web.member.application.MemberService;
import today.creame.web.member.application.model.MemberResult;
import today.creame.web.member.social.domain.ProviderType;
import today.creame.web.member.social.entrypoint.io.SocialMemberRegisterRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;
import today.creame.web.member.social.application.SocialService;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
public class SocialRestController implements BaseRestController {

    private final SocialService socialService;
    private final MemberService memberService;

    @PostMapping("/public/v1/oauth/{provider}")
    public ResponseEntity<Body<SimpleBodyData<String>>> initSocialUrl(@PathVariable ProviderType provider) {
        String loginUrl = socialService.initSocialUrl(provider);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData(loginUrl)));
    }

    @GetMapping("/public/v1/login/oauth/{provider}")
    public ResponseEntity<Body<Map<String, String>>> socialLogin(@PathVariable ProviderType provider, @RequestParam String code) {
        if(Objects.isNull(code)) {
            ResponseEntity.badRequest();
        }

        Map<String, String> token = socialService.login(provider, code);
        return ResponseEntity.ok(BodyFactory.success(token));
    }

    @PostMapping("/public/v1/oauth")
    public ResponseEntity<Body<SimpleBodyData<Long>>> registerSocialMember(
            @RequestBody @Valid SocialMemberRegisterRequest request, BindingResult bindingResult) {

        hasError(bindingResult);

        MemberResult member = memberService.registerMember(request.toParameter());

        SimpleBodyData<Long> result = new SimpleBodyData<>(member.getId());
        return ResponseEntity.ok(BodyFactory.success(result));
    }
}
