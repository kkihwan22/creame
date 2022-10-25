package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.PriceUnit;

@Converter
public class PriceUnitToStringConverter implements AttributeConverter<PriceUnit, String> {

    @Override
    public String convertToDatabaseColumn(PriceUnit attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public PriceUnit convertToEntityAttribute(String dbData) {
        return PriceUnit.valueOf(dbData);
    }
}
