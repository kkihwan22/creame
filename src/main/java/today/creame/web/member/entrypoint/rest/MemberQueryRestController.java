package today.creame.web.member.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.member.application.MemberQuery;
import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberNotificationPreference;
import today.creame.web.member.entrypoint.rest.io.MeResponse;
import today.creame.web.member.entrypoint.rest.io.MemberListResponse;
import today.creame.web.share.entrypoint.*;
import today.creame.web.share.support.SecurityContextSupporter;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MemberQueryRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(MemberQueryRestController.class);
    private final MemberQuery memberQuery;

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

    @GetMapping("/api/v1/me/notifications")
    public ResponseEntity<Body<MemberNotificationPreference>> myNotifications() {
        MemberNotificationPreference result = memberQuery.getNotificationSetting(SecurityContextSupporter.getId());
        return ResponseEntity.ok(BodyFactory.success(result));
    }

    @GetMapping("/admin/v1/members")
    public ResponseEntity<PageBody<MemberListResponse>> list(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable){

        Page<Member> memberPage = memberQuery.getList(pageable);
        List<MemberListResponse> responses = CollectionUtils.emptyIfNull(memberPage.getContent()).stream().map(MemberListResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(BodyFactory.success(responses, memberPage.getTotalElements()));
    }
}
