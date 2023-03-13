package today.creame.web.influence.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemMap {
    private final static Logger log = LoggerFactory.getLogger(ItemMap.class);
    private static final Map<Integer, Item> data;

    static {
        Map<Integer, Item> temp = new HashMap<>();
        Integer index = 0;
        Integer amount = 900;
        temp.put(index, new Item(index, amount, 30, TimeUnit.SECONDS));

        while (index < 12) {
            temp.put(++index, new Item(index, amount = amount + 100, 30, TimeUnit.SECONDS));
        }
        log.info("Item data: {}", temp);
        data = new HashMap<>(temp);
    }

    public static Item get(Integer index) {
        log.debug("ItemMap: {}", data);
        return ItemMap.data.get(index);
    }

    public static List<Item> get() {
        return data.values().stream().collect(Collectors.toList());
    }
}