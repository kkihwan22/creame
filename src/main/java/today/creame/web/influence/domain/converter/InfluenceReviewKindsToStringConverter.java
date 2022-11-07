package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.InfluenceReviewKinds;

@Converter
public class InfluenceReviewKindsToStringConverter implements AttributeConverter<InfluenceReviewKinds, String> {

    @Override
    public String convertToDatabaseColumn(InfluenceReviewKinds attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public InfluenceReviewKinds convertToEntityAttribute(String dbData) {
        return dbData != null
            ? InfluenceReviewKinds.valueOf(dbData)
            : null;
    }
}
