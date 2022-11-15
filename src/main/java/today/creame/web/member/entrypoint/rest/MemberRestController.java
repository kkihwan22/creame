package today.creame.web.member.entrypoint.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.member.application.MemberQuery;
import today.creame.web.member.application.MemberService;
import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.entrypoint.rest.io.MeResponse;
import today.creame.web.member.entrypoint.rest.io.MemberRegisterRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@Api("[ 회원 ] Api's")
@RequiredArgsConstructor
@RestController
public class MemberRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(MemberRestController.class);
    private final MemberService memberService;
    private final MemberQuery memberQuery;

    @ApiOperation(value = "회원가입", notes = "회원가입 시 사용하는 요청이다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 401, message = "휴대폰 본인인증 미확인"),
            @ApiResponse(code = 400, message = "잘못된 정보 전달"),
            @ApiResponse(code = 409, message = "중복된 정보 전달")
    })
    @PostMapping("/public/v1/members")
    public ResponseEntity<Body<SimpleBodyData<Long>>> registerMember(
            @RequestBody @Valid
            MemberRegisterRequest request,
            BindingResult bindingResult) {
        log.debug("[ request ] : {}", request);

        hasError(bindingResult);

        // todo: [ phone verified code 검증 로직 ]

        Long id = memberService.registerMember(request.toParameter());
        log.debug("register member id: {}", id);

        SimpleBodyData<Long> result = new SimpleBodyData<>(id);
        return ResponseEntity.ok(BodyFactory.success(result));
    }

    @GetMapping("/api/v1/me")
    public ResponseEntity<Body<MeResponse>> getMe(@AuthenticationPrincipal UserDetails userDetails) {
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        log.debug("customUserDetails: {}", customUserDetails);
        MeResult me = memberQuery.getMe(customUserDetails.getId());
        return ResponseEntity.ok(BodyFactory.success(new MeResponse(me)));
    }

    @GetMapping("/public/v1/members/phone-number/{phoneNumber}/exist")
    public ResponseEntity<Body<SimpleBodyData<Boolean>>> existMemberByPhoneNumber(@PathVariable String phoneNumber) {
        boolean result = memberQuery.existMemberByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(result)));
    }

    @GetMapping("/public/v1/members/email/{email}/exist")
    public ResponseEntity<Body<SimpleBodyData<Boolean>>> existMemberByEmail(@PathVariable String email) {
        boolean result = memberQuery.existMemberByEmail(email);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(result)));
    }

    @GetMapping("/public/v1/members/nickname/{nickname}/exist")
    public ResponseEntity<Body<SimpleBodyData<Boolean>>> existMemberByNickname(@PathVariable String nickname) {
        boolean result = memberQuery.existMemberByNickname(nickname);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(result)));
    }
}