package today.creame.web.share.event;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class MatchingEndEvent {
    private Long memberId;
    private Integer usedCoin;

    public MatchingEndEvent(Long memberId, Integer usedCoin) {
        this.memberId = memberId;
        this.usedCoin = usedCoin;
    }
}
