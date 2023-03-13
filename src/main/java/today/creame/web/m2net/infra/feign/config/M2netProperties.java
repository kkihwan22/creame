package today.creame.web.m2net.infra.feign.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "m2net")
@Setter
@Getter
@ToString
@Component
public class M2netProperties {
    private String cpid;
    private String authkey;
    private String host;
}
