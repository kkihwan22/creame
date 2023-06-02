package today.creame.web.matching.domain.converter;

import today.creame.web.matching.domain.MatchingProgressStatus;
import today.creame.web.matching.domain.PaidType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PaidTypeToStringConverter implements AttributeConverter<PaidType, String> {

    @Override
    public String convertToDatabaseColumn(PaidType attribute) {
        return (attribute != null)
                ? attribute.name()
                : null;
    }

    @Override
    public PaidType convertToEntityAttribute(String dbData) {
        return dbData != null
                ? PaidType.valueOf(dbData)
                : null;
    }
}
