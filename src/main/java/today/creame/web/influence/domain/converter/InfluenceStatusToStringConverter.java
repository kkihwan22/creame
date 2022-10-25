package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.InfluenceStatus;

@Converter
public class InfluenceStatusToStringConverter implements AttributeConverter<InfluenceStatus, String> {

    @Override
    public String convertToDatabaseColumn(InfluenceStatus attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public InfluenceStatus convertToEntityAttribute(String dbData) {
        return InfluenceStatus.valueOf(dbData);
    }
}
