package today.creame.web.member.application.model;

import lombok.Getter;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberRole;
import today.creame.web.member.domain.MemberRoleCode;
import today.creame.web.member.domain.MemberStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberDetailResult {
    private Long id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private String m2netUserId;
    private int coins;
    private int bonusCoins;
    private List<MemberRoleCode> memberRoleCodes;
    private MemberStatus status;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public MemberDetailResult (Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.phoneNumber = member.getPhoneNumber();
        this.m2netUserId = member.getM2netUserId();
        this.coins = member.getCoins();
        this.bonusCoins = member.getBonusCoins();
        this.memberRoleCodes = member.getRoles().stream().map(MemberRole::getCodeName).collect(Collectors.toList());
        this.status = member.getStatus();
        this.createdDt = member.getCreatedDateTime();
        this.updatedDt = member.getUpdatedDateTime();
    }
}
