package today.creame.web.share.event;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AutoChargingConfigEvent {
    private String mid;
    private String condition;

    public AutoChargingConfigEvent(String mid, String condition) {
        this.mid = mid;
        this.condition = condition;
    }
}
