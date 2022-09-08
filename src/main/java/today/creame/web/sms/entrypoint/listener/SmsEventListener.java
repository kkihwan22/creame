package today.creame.web.sms.entrypoint.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.member.application.model.SmsSendEvent;
import today.creame.web.sms.application.SmsService;

@RequiredArgsConstructor
@Component
public class SmsEventListener {
    private final Logger log = LoggerFactory.getLogger(SmsEventListener.class);
    private final SmsService smsService;

    @EventListener
    public void handleSmsSendEvent(SmsSendEvent event) {
        log.debug("event: {}", event);
        smsService.send(event.getPhoneNumber(), event.getDigit());
    }
}