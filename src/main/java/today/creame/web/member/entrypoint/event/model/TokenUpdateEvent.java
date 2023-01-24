package today.creame.web.member.entrypoint.event.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TokenUpdateEvent {
    private final Long memberId;
    private final String refreshToken;

    public TokenUpdateEvent(Long memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }
}
