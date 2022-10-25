package today.creame.web.sms.entrypoint.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.sms.application.SmsService;
import today.creame.web.sms.entrypoint.listener.event.SmsSendEvent;

@RequiredArgsConstructor
@Component
public class SmsEventListener {
    private final Logger log = LoggerFactory.getLogger(SmsEventListener.class);
    private final SmsService smsService;

    @EventListener
    public void listenSmsSendEvent(SmsSendEvent event) {
        log.debug("event: {}", event);
        smsService.send(event.getPhoneNumber(), event.getBody());
    }
}