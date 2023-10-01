package today.creame.web.influence.domain;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.ranking.domain.ConsultationProduct;

@Getter
@ToString
public class Item {
    private Long id;
    private int index;
    private int price;
    private int pricePerTime;
    private TimeUnit timeUnit;

    public Item(Long id, int index, int price, int pricePerTime, TimeUnit timeUnit) {
        this.id = id;
        this.index = index;
        this.price = price;
        this.pricePerTime = pricePerTime;
        this.timeUnit = timeUnit;
    }

    public Item(ConsultationProduct consultationProduct) {
        this(consultationProduct.getId(), consultationProduct.getOrderNo(), consultationProduct.getBudgetAmount(), consultationProduct.getPricePerTime(), TimeUnit.SECONDS);
    }
}
