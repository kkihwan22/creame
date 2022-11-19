package today.creame.web.share.aspect.permit;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(value = FIELD)
public @interface PermitRule {
    PermitRuleType type();
}
