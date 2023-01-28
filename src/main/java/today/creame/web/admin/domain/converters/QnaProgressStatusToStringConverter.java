package today.creame.web.admin.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.admin.domain.QnaProgressStatus;

@Converter
public class QnaProgressStatusToStringConverter implements AttributeConverter<QnaProgressStatus, String> {
    @Override
    public String convertToDatabaseColumn(QnaProgressStatus attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public QnaProgressStatus convertToEntityAttribute(String dbData) {
        return dbData != null
            ? QnaProgressStatus.valueOf(dbData)
            : null;
    }
}
