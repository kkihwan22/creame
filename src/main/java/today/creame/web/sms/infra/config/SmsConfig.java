package today.creame.web.sms.infra.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "services.external.sms")
@Getter
@ToString
public class SmsConfig {
    private final Logger log = LoggerFactory.getLogger(SmsConfig.class);
    @Setter
    private String host;
    private String apiKey;
    private String secretKey;
}