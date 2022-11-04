package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.Rank;

@Converter
public class InfluenceRankToStringConverter implements AttributeConverter<Rank, String> {

    @Override
    public String convertToDatabaseColumn(Rank attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public Rank convertToEntityAttribute(String dbData) {
        return dbData != null
            ? Rank.valueOf(dbData)
            : null;
    }
}
