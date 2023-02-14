package today.creame.web.m2net.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.m2net.domain.M2netReasonCode;

@Converter
public class M2netReasonCodeToStringConverter implements AttributeConverter<M2netReasonCode, String> {

    @Override
    public String convertToDatabaseColumn(M2netReasonCode attribute) {
        return attribute.name();
    }

    @Override
    public M2netReasonCode convertToEntityAttribute(String dbData) {
        return M2netReasonCode.valueOf(dbData);
    }
}
