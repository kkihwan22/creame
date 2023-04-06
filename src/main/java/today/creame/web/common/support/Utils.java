package today.creame.web.common.support;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;

import java.util.Collection;

import static org.springframework.util.CollectionUtils.isEmpty;

public class Utils {

    public static BooleanExpression inOperation(SimpleExpression column, Collection value) {
        if (isEmpty(value)) return null;

        return column.in(value);
    }
}
