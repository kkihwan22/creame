package today.creame.web.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import today.creame.web.config.security.CreameAuthenticationFilter;
import today.creame.web.config.security.CreameAuthorizationFilter;
import today.creame.web.config.security.SecurityErrorHandleFilter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
    private final UserDetailsService creameUserDetailsServiceImpl;
    private final ApplicationEventPublisher applicationEventPublisher;

    private static final String[] PERMIT_URL_ARRAY = {
            "/_health", "/public/**", "/pages/**", "/v3/api-docs/**", "/swagger-ui/**",
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(creameUserDetailsServiceImpl).passwordEncoder(passwordEncoder());
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CreameAuthenticationFilter creameAuthenticationFilter = new CreameAuthenticationFilter(authenticationManagerBean());
        creameAuthenticationFilter.setApplicationEventPublisher(applicationEventPublisher);
        creameAuthenticationFilter.setFilterProcessesUrl("/public/*/login");

        http.csrf().disable().httpBasic().disable().formLogin().disable().logout().disable();

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors().configurationSource(corsConfigurationSource());

        http.authorizeHttpRequests(request -> {
            request.antMatchers(PERMIT_URL_ARRAY).permitAll();
            request.antMatchers("/admin/**")
                    .hasRole("ADMIN");

            // TODO: 이 부분 좀 더 공부할 것!
            request.anyRequest()
                    .authenticated();

            http.addFilter(creameAuthenticationFilter);
            http.addFilterBefore(new CreameAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
            http.addFilterBefore(new SecurityErrorHandleFilter(), CreameAuthorizationFilter.class);
        });

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(Arrays.stream(HttpMethod.values()).map(Enum::name).collect(Collectors.toList()));
        config.setMaxAge(0L);
        config.setAllowCredentials(false);

        final UrlBasedCorsConfigurationSource urlBasedCorsConfigSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigSource.registerCorsConfiguration("/**", config);
        return urlBasedCorsConfigSource;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}