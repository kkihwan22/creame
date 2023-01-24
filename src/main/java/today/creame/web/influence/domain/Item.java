package today.creame.web.influence.domain;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Item {
    private int index;
    private int price;
    private int pricePerTime;
    private TimeUnit timeUnit;

    public Item(int index, int price, int pricePerTime, TimeUnit timeUnit) {
        this.index = index;
        this.price = price;
        this.pricePerTime = pricePerTime;
        this.timeUnit = timeUnit;
    }
}
