package today.creame.web.matching.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.matching.domain.MatchingStatus;

@Converter
public class MatchingStatusToStringConverter implements AttributeConverter<MatchingStatus, String> {

    @Override
    public String convertToDatabaseColumn(MatchingStatus attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public MatchingStatus convertToEntityAttribute(String dbData) {
        return dbData != null
            ? MatchingStatus.valueOf(dbData)
            : null;
    }
}
