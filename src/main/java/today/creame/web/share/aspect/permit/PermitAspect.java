package today.creame.web.share.aspect.permit;

import java.lang.reflect.Field;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import today.creame.web.share.model.BaseParameter;

@Component
@Aspect
public class PermitAspect {
    private final Logger log = LoggerFactory.getLogger(PermitAspect.class);

    @Pointcut("@annotation(today.creame.web.share.aspect.permit.Permit)")
    public void permitAdvice() {
    }

    @Before("permitAdvice()")
    public void aspect(JoinPoint joinPoint) throws NoSuchFieldException, IllegalAccessException {
        Object o = Arrays.stream(joinPoint.getArgs())
            .filter(arg -> arg instanceof BaseParameter)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Not found Permit rules.{}"));

        Field f = Arrays.stream(o.getClass().getDeclaredFields())
            .filter(field -> field.isAnnotationPresent(PermitRule.class))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Not found Permit rules.{}"));

        f.setAccessible(true);

        PermitRule annotation = f.getAnnotation(PermitRule.class);
        PermitRuleType type = annotation.type();
        type.valid(f.get(o));
    }
}
