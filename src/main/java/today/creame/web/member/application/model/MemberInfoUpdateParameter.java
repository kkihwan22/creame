package today.creame.web.member.application.model;

import lombok.Getter;
import today.creame.web.member.entrypoint.rest.io.MemberInfoUpdateRequest;

@Getter
public class MemberInfoUpdateParameter {
    private Long memberId;
    private String nickname;
    private String phoneNumber;

    public MemberInfoUpdateParameter (Long memberId, MemberInfoUpdateRequest request) {
        this.memberId = memberId;
        this.nickname = request.getNickname();
        this.phoneNumber = request.getPhoneNumber();
    }
}
