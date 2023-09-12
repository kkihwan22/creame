package today.creame.web.member.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.member.domain.MemberStatus;

import java.time.LocalDateTime;

@Getter
public class MemberListResponse {
    private Long id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private MemberStatus status;
    private Long reviewCount;
    private Long qnaCount;
    private Long matchingCount;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public MemberListResponse(Long id, String email, String nickname, String phoneNumber, MemberStatus status,
                              Long reviewCount, Long qnaCount, Long matchingCount, LocalDateTime createdDt, LocalDateTime updatedDt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.reviewCount = reviewCount;
        this.qnaCount = qnaCount;
        this.matchingCount = matchingCount;
        this.createdDt = createdDt;
        this.updatedDt = updatedDt;
    }
}
