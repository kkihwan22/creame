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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import today.creame.web.member.application.MemberService;
import today.creame.web.member.application.model.*;
import today.creame.web.member.domain.NotificationSettingItem;
import today.creame.web.member.entrypoint.rest.io.*;
import today.creame.web.share.domain.OnOffCondition;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;
import today.creame.web.share.entrypoint.validator.EnumConstraint;
import today.creame.web.share.support.RandomString;

@Api("[ 회원 ] Api's")
@RequiredArgsConstructor
@RestController
public class MemberRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(MemberRestController.class);
    private final MemberService memberService;

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

        MemberResult member = memberService.registerMember(request.toParameter());
        log.debug("register member id: {}", member.getId());

        SimpleBodyData<Long> result = new SimpleBodyData<>(member.getId());
        return ResponseEntity.ok(BodyFactory.success(result));
    }

    @PostMapping("/public/v1/forget-email")
    public ResponseEntity<Body<SimpleBodyData<String>>> forgetMyEmail(
        @RequestBody @Valid ForgetEmailRequest request,
        BindingResult bindingResult) {
        log.debug("request: {}", request);

        String result = memberService.forgetEmail(new ForgetEmailParameter(request.getToken(), request.getPhoneNumber()));
        log.debug("result: {}", request);

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(result)));
    }

    @PostMapping("/public/v1/forget-password")
    public ResponseEntity<Body<SimpleBodyData<String>>> forgetMyPassword(
        @RequestBody @Valid ForgetPasswordRequest request,
        BindingResult bindingResult
    ) {
        log.debug("request: {}", request);

        hasError(bindingResult);

        memberService.forgetPassword(new ForgetPasswordParameter(
            request.getEmail(),
            request.getPhoneNumber(),
            request.getToken()));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("SMS 발송")));
    }

    @PatchMapping("/api/v1/members/{id}/nickname")
    public ResponseEntity<Body<SimpleBodyData<String>>> updateNickname(
        @PathVariable Long id,
        @RequestBody @Valid MemberNicknameUpdateRequest request, BindingResult bindingResult
    ) {
        hasError(bindingResult);
        memberService.updateNickname(new MemberUpdateParameter(id, null, request.getNickname()));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/api/v1/members/{id}/password")
    public ResponseEntity<Body<SimpleBodyData<String>>> updatePassword(
        @PathVariable Long id,
        @RequestBody @Valid MemberPasswordUpdateRequest request, BindingResult bindingResult
    ) {
        hasError(bindingResult);
        memberService.updatePassword(new MemberUpdateParameter(id, request.getChangedPassword(), null));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/api/v1/members/{id}/notifications/{item}/{condition}")
    public ResponseEntity<Body<SimpleBodyData<String>>> changeNotificationSettings(
        @PathVariable Long id,
        @PathVariable @EnumConstraint(enumClass = NotificationSettingItem.class, ignoreCase = true) NotificationSettingItem item,
        @PathVariable @EnumConstraint(enumClass = OnOffCondition.class, ignoreCase = true) OnOffCondition condition
    ) {
        memberService.changedNotificationCondition(
            new NotificationSettingParameter(id, item, condition.getCondition()));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/api/v1/me/phone-number")
    public ResponseEntity<Body<SimpleBodyData<String>>> changedPhoneNumber(
        @RequestBody @Valid PhoneNumberUpdateRequest request,
        BindingResult bindingResult) {
        hasError(bindingResult);
        memberService.changedPhoneNumber(request.getToken(), request.getPhoneNumber());
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @GetMapping("/admin/v1/members/{id}")
    public ResponseEntity<Body<MemberDetailResponse>> get(@PathVariable Long id) {
        MemberDetailResult result = memberService.getDetail(id);
        MemberDetailResponse response = new MemberDetailResponse(result);

        return ResponseEntity.ok(BodyFactory.success(response));

    }

    @PatchMapping("/admin/v1/members/{id}")
    public ResponseEntity<Body<String>> changeMemberInfo(@PathVariable Long id, @RequestBody MemberInfoUpdateRequest request) {
        memberService.changeMemberInfo(new MemberInfoUpdateParameter(id, request));
        return ResponseEntity.ok(BodyFactory.success("success"));
    }

    @PatchMapping("/admin/v1/members/{id}/password")
    public ResponseEntity<Body<String>> changePasswordByAdmin(@PathVariable Long id) {
        memberService.changePasswordByAdmin(id);

        return ResponseEntity.ok(BodyFactory.success("success"));
    }
}