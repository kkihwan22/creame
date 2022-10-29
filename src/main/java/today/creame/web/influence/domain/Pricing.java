package today.creame.web.influence.domain;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.influence.domain.converter.PriceTimeUnitToStringConverter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter @ToString
public class Pricing {
    private int price;
    private int priceTime;
    @Convert(converter = PriceTimeUnitToStringConverter.class)
    private PriceTimeUnit priceTimeUnit;

    public Pricing(int price, int priceTime, PriceTimeUnit priceTimeUnit) {
        this.price = price;
        this.priceTime = priceTime;
        this.priceTimeUnit = priceTimeUnit;
    }
}
