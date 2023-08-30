package today.creame.web.sms.application;

import org.junit.jupiter.api.Test;

class SmsTemplateTest {

    @Test
    void template() {
        String template = SmsTemplate.influenceWelcome("Asbd124");
        System.out.println(template);
    }

}