package today.creame.web.member.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberStatus;

import java.time.LocalDateTime;

@Getter
public class MemberListResponse {
    private Long id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private MemberStatus status;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public MemberListResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.phoneNumber = member.getPhoneNumber();
        this.status = member.getStatus();
        this.createdDt = member.getCreatedDateTime();
        this.updatedDt = member.getUpdatedDateTime();
    }


}
