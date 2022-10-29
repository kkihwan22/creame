package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.PriceTimeUnit;

@Converter
public class PriceTimeUnitToStringConverter implements AttributeConverter<PriceTimeUnit, String> {

    @Override
    public String convertToDatabaseColumn(PriceTimeUnit attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public PriceTimeUnit convertToEntityAttribute(String dbData) {
        return PriceTimeUnit.valueOf(dbData);
    }
}
