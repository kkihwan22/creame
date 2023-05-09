package today.creame.web.payments.domain.converter;

import today.creame.web.payments.domain.PaymentMethod;
import today.creame.web.payments.domain.PaymentsHistoryStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PaymentMethodToStringConverter implements AttributeConverter<PaymentMethod, String> {

    @Override
    public String convertToDatabaseColumn(PaymentMethod attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String dbData) {
        return dbData != null
            ? PaymentMethod.valueOf(dbData)
            : null;
    }
}
