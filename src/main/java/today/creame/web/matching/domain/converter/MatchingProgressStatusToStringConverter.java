package today.creame.web.matching.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.matching.domain.MatchingProgressStatus;

@Converter
public class MatchingProgressStatusToStringConverter implements AttributeConverter<MatchingProgressStatus, String> {

    @Override
    public String convertToDatabaseColumn(MatchingProgressStatus attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public MatchingProgressStatus convertToEntityAttribute(String dbData) {
        return dbData != null
            ? MatchingProgressStatus.valueOf(dbData)
            : null;
    }
}
