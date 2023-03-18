package today.creame.web.sms.entrypoint.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import today.creame.web.share.event.SmsSendEvent;
import today.creame.web.sms.application.SmsService;

@RequiredArgsConstructor
@Component
public class SmsEventListener {
    private final Logger log = LoggerFactory.getLogger(SmsEventListener.class);
    private final SmsService smsService;

    @Async
    @TransactionalEventListener
    public void eventSendSms(SmsSendEvent event) {
        log.debug("event: {}", event);
        smsService.send(event.getPhoneNumber(), event.getBody());
    }
}