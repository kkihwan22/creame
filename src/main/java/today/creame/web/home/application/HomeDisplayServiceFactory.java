package today.creame.web.home.application;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import today.creame.web.share.support.ApplicationContextSupporter;

@Component
public class HomeDisplayServiceFactory {
    private final Logger log = LoggerFactory.getLogger(HomeDisplayServiceFactory.class);

    enum ListType {
        ALL("homeDisplayListAllServiceImpl"),
        RECOMMEND("homeDisplayRecommendServiceImpl"),
        LATEST("homeDisplayLatestServiceImpl"),
        HOT("homeDisplayHotServiceImpl"),
        CALLING("homeDisplayCallingServiceImpl"),
        CATEGORY("homeDisplayByCategoryServiceImpl"),
        KEYWORD("homeDisplayByKeywordServiceImpl"),

        ;

        @Getter
        private String beanName;

        ListType(String beanName) {
            this.beanName = beanName;
        }
    }

    public HomeDisplayService factory(String type) {
        String beanName = ListType.valueOf(type.toUpperCase()).getBeanName();
        return (HomeDisplayService) ApplicationContextSupporter.getBean(beanName);
    }
}
