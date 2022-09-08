package today.creame.web.sms.infra;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import today.creame.web.sms.application.adapter.SmsSender;
import today.creame.web.sms.infra.config.SmsConfig;
import today.creame.web.sms.infra.feign.CoolSmsClient;
import today.creame.web.sms.infra.support.SaltSupport;
import today.creame.web.sms.infra.support.SignatureSupport;

import java.time.LocalDateTime;
import java.util.Map;


@RequiredArgsConstructor
@Component
public class CoolSmsSenderImpl implements SmsSender {
    private final Logger log = LoggerFactory.getLogger(CoolSmsSenderImpl.class);
    private final String from = "0220881955";
    private final SmsConfig property;
    private final CoolSmsClient coolSmsClient;

    @Override
    public void sendOne(String to, String content) {
        LocalDateTime datetime = LocalDateTime.now();
        String salt = SaltSupport.generate();
        ResponseEntity<CoolSmsClient.SimpleMessageResponse> response =
                coolSmsClient.sendOne(
                        authorization(
                                property.getApiKey(),
                                datetime,
                                salt,
                                SignatureSupport.generateSignature(property.getSecretKey(), datetime, salt)),
                        Map.of("message", new CoolSmsClient.SimpleMessageRequest(to, from, content))
                );
        log.debug("response: {}", response);
    }

    private String authorization(String apiKey, LocalDateTime date, String salt, String signature) {
        return new StringBuilder()
                .append("HMAC-SHA256")
                .append(" ")
                .append("apiKey=")
                .append(apiKey)
                .append(", ")
                .append("date=")
                .append(date)
                .append(", ")
                .append("salt=")
                .append(salt)
                .append(", ")
                .append("signature=")
                .append(signature)
                .toString();
    }
}
