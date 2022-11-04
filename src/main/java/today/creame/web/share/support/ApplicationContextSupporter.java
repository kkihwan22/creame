package today.creame.web.share.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextSupporter implements ApplicationContextAware {

    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ac = context;
    }

    public static Object getBean(String name) {
        return ac.getBean(name);
    }

    public static String[] getBeanNamesForType(Class<?> type) {
        return ac.getBeanNamesForType(type);
    }

}
