package today.creame.web.m2net.infra.feign.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class M2netHeaderConfig {

    @Bean
    public RequestInterceptor requestInterceptor(@Autowired M2netProperties properties) {
        return requestTemplate -> {
            // TODO: 설정으로 분리
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Authorization", properties.getAuthkey());
        };
    }
}
