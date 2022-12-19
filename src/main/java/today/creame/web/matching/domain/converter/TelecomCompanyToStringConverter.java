package today.creame.web.matching.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.matching.domain.TelecomCompanyCode;

@Converter
public class TelecomCompanyToStringConverter implements AttributeConverter<TelecomCompanyCode, String> {

    @Override
    public String convertToDatabaseColumn(TelecomCompanyCode attribute) {
        return (attribute != null)
            ? attribute.getLabel()
            : null;
    }

    @Override
    public TelecomCompanyCode convertToEntityAttribute(String dbData) {
        return dbData != null
            ? TelecomCompanyCode.of(dbData)
            : null;
    }
}
