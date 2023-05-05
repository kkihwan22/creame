package today.creame.web.payments.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import today.creame.web.payments.domain.PaymentsHistoryStatus;

@Converter
public class PaymentsHistoryTypeToStringConverter implements AttributeConverter<PaymentsHistoryStatus, String> {

    @Override
    public String convertToDatabaseColumn(PaymentsHistoryStatus attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public PaymentsHistoryStatus convertToEntityAttribute(String dbData) {
        return dbData != null
            ? PaymentsHistoryStatus.valueOf(dbData)
            : null;
    }
}
