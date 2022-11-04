package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.InfluenceApplicationStatus;

@Converter
public class InfluenceApplicationStatusToStringConverter implements AttributeConverter<InfluenceApplicationStatus, String> {

    @Override
    public String convertToDatabaseColumn(InfluenceApplicationStatus attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;

    }

    @Override
    public InfluenceApplicationStatus convertToEntityAttribute(String dbData) {
        return dbData != null
            ? InfluenceApplicationStatus.valueOf(dbData)
            : null;
    }
}