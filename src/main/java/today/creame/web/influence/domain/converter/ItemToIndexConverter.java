package today.creame.web.influence.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.influence.domain.Item;
import today.creame.web.influence.domain.ItemMap;

@Converter
public class ItemToIndexConverter implements AttributeConverter<Item, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Item item) {
        return item.getIndex();
    }

    @Override
    public Item convertToEntityAttribute(Integer index) {
        return ItemMap.get(index);
    }
}
