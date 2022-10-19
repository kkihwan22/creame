package today.creame.web.share.entrypoint.converter;

import org.springframework.core.convert.converter.Converter;
import today.creame.web.influence.domain.Category;

public class NameToCategoryConverter implements Converter<String, Category> {

    @Override
    public Category convert(String name) {
        return Category.valueOf(name);
    }
}
