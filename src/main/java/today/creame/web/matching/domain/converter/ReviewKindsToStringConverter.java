package today.creame.web.matching.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.matching.domain.ReviewKinds;

@Converter
public class ReviewKindsToStringConverter implements AttributeConverter<ReviewKinds, String> {

    @Override
    public String convertToDatabaseColumn(ReviewKinds attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public ReviewKinds convertToEntityAttribute(String dbData) {
        return dbData != null
            ? ReviewKinds.valueOf(dbData)
            : null;
    }
}
