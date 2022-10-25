package today.creame.web.influence.domain;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.influence.domain.converter.PriceUnitToStringConverter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter @ToString
public class Pricing {
    private int price;
    @Convert(converter = PriceUnitToStringConverter.class)
    private PriceUnit priceUnit;

    public Pricing(int price, PriceUnit priceUnit) {
        this.price = price;
        this.priceUnit = priceUnit;
    }
}
