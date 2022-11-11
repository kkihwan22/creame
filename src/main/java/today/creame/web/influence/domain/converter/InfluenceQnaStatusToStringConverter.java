package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.InfluenceStatus;
import today.creame.web.influence.domain.QnaStatus;

@Converter
public class InfluenceQnaStatusToStringConverter implements AttributeConverter<QnaStatus, String> {

    @Override
    public String convertToDatabaseColumn(QnaStatus attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public QnaStatus convertToEntityAttribute(String dbData) {
        return dbData != null
            ? QnaStatus.valueOf(dbData)
            : null;
    }
}
