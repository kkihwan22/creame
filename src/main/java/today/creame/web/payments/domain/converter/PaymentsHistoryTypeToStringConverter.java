package today.creame.web.payments.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.payments.domain.PaymentsHistoryType;

@Converter
public class PaymentsHistoryTypeToStringConverter implements AttributeConverter<PaymentsHistoryType, String> {

    @Override
    public String convertToDatabaseColumn(PaymentsHistoryType attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public PaymentsHistoryType convertToEntityAttribute(String dbData) {
        return dbData != null
            ? PaymentsHistoryType.valueOf(dbData)
            : null;
    }
}
