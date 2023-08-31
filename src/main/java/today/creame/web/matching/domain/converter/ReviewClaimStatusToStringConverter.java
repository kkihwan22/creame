package today.creame.web.matching.domain.converter;

import today.creame.web.matching.domain.ReviewClaimStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ReviewClaimStatusToStringConverter implements AttributeConverter<ReviewClaimStatus, String> {

    @Override
    public String convertToDatabaseColumn(ReviewClaimStatus attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public ReviewClaimStatus convertToEntityAttribute(String dbData) {
        return dbData != null
            ? ReviewClaimStatus.valueOf(dbData)
            : null;
    }
}
