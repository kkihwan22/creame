package today.creame.web.sms.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.sms.entrypoint.listener.SmsEventListener;

@RequiredArgsConstructor
@Service
public class SmsServiceImpl implements SmsService {
    private final Logger log = LoggerFactory.getLogger(SmsEventListener.class);
    private final SmsSender sender;

    @Override
    public void send(String to, String content) {
        log.debug("execute send sms. to : {}, content : {}", to, content);
        sender.sendOne(to, content);
    }
}