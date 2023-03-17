package today.creame.web.share.support;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

@Getter
@ToString
public class SortSupport {
    private static final Logger log = LoggerFactory.getLogger(SortSupport.class);
    private static final String MAJOR_SEPARATOR = ",";
    private static final String MINOR_SEPARATOR = "|";

    public static Sort convert(String sort) {
        if (StringUtils.isEmpty(sort)) {
            return Sort.unsorted();
        }

        String[] properties = sort.split(MAJOR_SEPARATOR);
        log.debug("properties:{}", properties.length);

        List<Order> orders = new ArrayList<>();
        for (String order : properties) {
            String[] split = order.split(MINOR_SEPARATOR);
            Direction direction = Direction.fromString(split[1]);
            orders.add(new Order(direction, split[0]));
        }
        return Sort.by(orders);
    }
}
