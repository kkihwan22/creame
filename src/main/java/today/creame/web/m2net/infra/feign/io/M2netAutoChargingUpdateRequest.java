package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.event.AutoChargingConfigEvent;

@Getter
@ToString
public class M2netAutoChargingUpdateRequest {
    private String autopayflag;

    public M2netAutoChargingUpdateRequest(AutoChargingConfigEvent event) {
        this.autopayflag = event.getCondition();
    }
}
