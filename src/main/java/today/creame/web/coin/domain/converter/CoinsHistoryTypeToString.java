package today.creame.web.coin.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.coin.domain.CoinsHistoryType;

@Converter
public class CoinsHistoryTypeToString implements AttributeConverter<CoinsHistoryType, String> {

    @Override
    public String convertToDatabaseColumn(CoinsHistoryType attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public CoinsHistoryType convertToEntityAttribute(String dbData) {
        return dbData != null
            ? CoinsHistoryType.valueOf(dbData)
            : null;
    }
}
