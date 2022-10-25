package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.SnsCompany;

@Converter
public class SnsCompanyToStringConverter implements AttributeConverter<SnsCompany, String> {

    @Override
    public String convertToDatabaseColumn(SnsCompany attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public SnsCompany convertToEntityAttribute(String dbData) {
        return SnsCompany.valueOf(dbData);
    }
}
