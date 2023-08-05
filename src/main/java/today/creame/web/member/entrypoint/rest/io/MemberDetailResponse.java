package today.creame.web.member.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.member.application.model.MemberDetailResult;
import today.creame.web.member.domain.MemberRoleCode;
import today.creame.web.member.domain.MemberStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter

public class MemberDetailResponse {
    private Long id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private String m2netUserId;
    private int coins;
    private int bonusCoins;
    List<MemberRoleCode> memberRoleCodes;
    private MemberStatus status;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public MemberDetailResponse (MemberDetailResult result) {
        this.id = result.getId();
        this.email = result.getEmail();
        this.nickname = result.getNickname();
        this.phoneNumber = result.getPhoneNumber();
        this.m2netUserId = result.getM2netUserId();
        this.coins = result.getCoins();
        this.bonusCoins = result.getBonusCoins();
        this.memberRoleCodes = result.getMemberRoleCodes();
        this.status = result.getStatus();
        this.createdDt = result.getCreatedDt();
        this.updatedDt = result.getUpdatedDt();

    }
}
