package today.creame.web.member.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberToken;

@Getter @ToString
public class RefreshTokenParameter {

    private final Long memberId;
    private final String refreshToken;

    public RefreshTokenParameter(Long memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }

    public MemberToken toEntity() {
        return new MemberToken(null, new Member(memberId), this.refreshToken);
    }
}