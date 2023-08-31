package today.creame.web.matching.domain.converter;

import today.creame.web.matching.domain.ReviewClaimKinds;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ReviewClaimKindsToStringConverter implements AttributeConverter<ReviewClaimKinds, String> {

    @Override
    public String convertToDatabaseColumn(ReviewClaimKinds attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public ReviewClaimKinds convertToEntityAttribute(String dbData) {
        return dbData != null
            ? ReviewClaimKinds.valueOf(dbData)
            : null;
    }
}
