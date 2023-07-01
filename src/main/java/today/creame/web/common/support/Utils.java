package today.creame.web.common.support;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import today.creame.web.common.domain.FileResource;

import java.util.Collection;
import java.util.Objects;

import static org.springframework.util.CollectionUtils.isEmpty;

public class Utils {

    public static BooleanExpression inOperation(SimpleExpression column, Collection value) {
        if (isEmpty(value)) return null;

        return column.in(value);
    }

    public static String combineFileResourceUrl(FileResource file) {
        if (Objects.isNull(file)) return null;

        return file.getContextName() + file.getBucketName() + file.getObjectKey();
    }

    public static BooleanExpression equalsOperation(SimpleExpression column, Object value) {
        if (Objects.isNull(value)) return null;

        return column.eq(value);
    }
}
