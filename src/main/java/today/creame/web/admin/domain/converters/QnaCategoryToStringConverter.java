package today.creame.web.admin.domain.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.admin.domain.QnaCategory;

@Converter
public class QnaCategoryToStringConverter implements AttributeConverter<QnaCategory, String> {
    @Override
    public String convertToDatabaseColumn(QnaCategory attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public QnaCategory convertToEntityAttribute(String dbData) {
        return dbData != null
            ? QnaCategory.valueOf(dbData)
            : null;
    }
}
