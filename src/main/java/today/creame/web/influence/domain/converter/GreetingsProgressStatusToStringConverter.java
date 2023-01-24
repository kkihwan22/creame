package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.GreetingsProgressStatus;

@Converter
public class GreetingsProgressStatusToStringConverter implements AttributeConverter<GreetingsProgressStatus, String> {

    @Override
    public String convertToDatabaseColumn(GreetingsProgressStatus attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public GreetingsProgressStatus convertToEntityAttribute(String dbData) {
        return dbData != null
            ? GreetingsProgressStatus.valueOf(dbData)
            : null;
    }
}
