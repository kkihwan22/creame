package today.creame.web.common.support;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.*;
import today.creame.web.common.domain.FileResource;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

import static com.querydsl.core.types.ExpressionUtils.template;
import static org.springframework.util.CollectionUtils.isEmpty;

public class Utils {

    public static BooleanExpression inOperation(SimpleExpression column, Collection value) {
        if (isEmpty(value)) return null;

        return column.in(value);
    }

    public static String combineFileResourceUrl(FileResource file) {
        if (Objects.isNull(file)) return null;

        return file.getContextName() + file.getBucketName() + "/" + file.getObjectKey();
    }

    public static BooleanExpression equalsOperation(SimpleExpression column, Object value) {
        if (Objects.isNull(value)) return null;

        return column.eq(value);
    }

    public static DateExpression dateFormat(DateTimePath column, String pattern) {
        Expression<String> strFormat = template(String.class, "DATE_FORMAT({0}, {1})", column, pattern);
        DateExpression dateFormat = Expressions.dateTemplate(LocalDate.class, "STR_TO_DATE({0}, {1})", strFormat, pattern);
        return dateFormat;
    }

    public static BooleanExpression dateBetween(DateExpression standardDate, LocalDate startDt, LocalDate endDt) {
        if(Objects.isNull(startDt) || Objects.isNull(endDt)) {
            return null;
        }
        return standardDate.goe(startDt).and(standardDate.loe(endDt));
    }
}
