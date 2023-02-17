package today.creame.web.share.support;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import today.creame.web.home.application.DisplayQuery;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationContextSupporterTest {
    private final Logger log = LoggerFactory.getLogger(ApplicationContextSupporter.class);

    //@Disabled("ApplicationContextSupporter 기능 확인을 위한 임시 테스트")
    @DisplayName("ApplicationContextSupporter 기능 중 해당 타입의 빈 목록 조회")
    @Test
    public void beanNames_for_type() {
        String[] beanNames = ApplicationContextSupporter.getBeanNamesForType(DisplayQuery.class);
        for (String beanName: beanNames) {
            log.debug("beanName: {}", beanName);
        }
        assertTrue(beanNames.length > 1);
    }
}