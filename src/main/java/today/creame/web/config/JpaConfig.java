package today.creame.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("today.creame.web")
@Configuration
public class JpaConfig {
}
