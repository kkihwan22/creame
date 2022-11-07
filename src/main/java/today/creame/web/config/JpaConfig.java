package today.creame.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("today.creame.web")
@EnableJpaAuditing
@Configuration
public class JpaConfig {
}
