package today.creame.web.sms.infra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CoolSmsSenderImplTest {

    @Autowired
    private CoolSmsSenderImpl sender;

    @Test
    void test_send_success() {
        // TODO: 테스트 시, Logging Level 적용 확인
        sender.sendOne("01039960399", "[ test ] 발송성공.!");
    }
}