package today.creame.web.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class CloudConfig {
    private final Logger log = LoggerFactory.getLogger(CloudConfig.class);
}


