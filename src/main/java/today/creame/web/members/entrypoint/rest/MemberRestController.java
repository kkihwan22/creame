package today.creame.web.members.entrypoint.rest;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.members.applications.MemberService;
import today.creame.web.members.applications.model.MemberRegisterParameter;
import today.creame.web.share.entrypoint.ResponseBody;
import today.creame.web.share.entrypoint.ResponseBodyFactory;

@RequiredArgsConstructor
@RestController
public class MemberRestController {
    private final Logger log = LoggerFactory.getLogger(MemberRestController.class);
    private final MemberService memberService;

    @PostMapping("/v1/members")
    public ResponseEntity<ResponseBody> registerMember(MemberRegisterParameter parameter) {
        memberService.registerMember(parameter);
        return ResponseEntity.ok(ResponseBodyFactory.success(Void.class));
    }
}