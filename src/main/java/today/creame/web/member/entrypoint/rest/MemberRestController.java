package today.creame.web.member.entrypoint.rest;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.member.application.MemberService;
import today.creame.web.member.entrypoint.rest.model.MemberRegisterRequest;
import today.creame.web.share.entrypoint.ResponseBody;
import today.creame.web.share.entrypoint.ResponseBodyFactory;
import today.creame.web.share.entrypoint.SimpleResponseData;

@RequiredArgsConstructor
@RestController
public class MemberRestController {
    private final Logger log = LoggerFactory.getLogger(MemberRestController.class);
    private final MemberService memberService;

    @PostMapping("/v1/members")
    public ResponseEntity<ResponseBody<SimpleResponseData<Long>>> registerMember(@RequestBody MemberRegisterRequest request, BindingResult bindingResult) {
        log.debug("[ request ] : {}", request);
        Long id = memberService.registerMember(request.to());

        log.debug("register member id: {}", id);
        SimpleResponseData<Long> result = new SimpleResponseData<>(id);
        return ResponseEntity.ok(ResponseBodyFactory.success(result));
    }
}