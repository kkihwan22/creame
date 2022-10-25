package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.Category;

@Converter
public class CategoryToStringConverter implements AttributeConverter<Category, String> {
    @Override
    public String convertToDatabaseColumn(Category attribute) {
        return (attribute != null)
            ? attribute.name()
            : null;
    }

    @Override
    public Category convertToEntityAttribute(String dbData) {
        return Category.valueOf(dbData);
    }
}
