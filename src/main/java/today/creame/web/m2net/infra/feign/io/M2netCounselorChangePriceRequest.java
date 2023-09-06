package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.Item;

@Getter @ToString
public class M2netCounselorChangePriceRequest {
    private Integer decamt; // 차감금액
    private Integer dectm;  // 차감시간

    public M2netCounselorChangePriceRequest(Item item) {
        this.decamt = item.getPrice();
        this.dectm = item.getPricePerTime();
    }

}
