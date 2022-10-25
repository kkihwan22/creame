package today.creame.web.config.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import today.creame.web.member.domain.Token;
import today.creame.web.member.domain.TokenType;
import today.creame.web.member.entrypoint.listner.event.RefreshTokenEvent;
import today.creame.web.share.entrypoint.BodyFactory;


public class CreameAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final static Logger log = LoggerFactory.getLogger(CreameAuthenticationFilter.class);
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper mapper = new ObjectMapper();

    public CreameAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        log.debug("[ filter ] execute authentication filter.");

        LoginRequest loginRequest;
        try {
            loginRequest = mapper.readValue(request.getReader().lines().collect(Collectors.joining()), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new AuthenticationServiceException(" request parsing error. ");
        }

        log.debug(" [ LoginRequest ] {}", loginRequest);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        return authenticationManager.authenticate(token);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        Token refresh = TokenType.REFRESH_TOKEN.factory(user);
        log.info("[ refresh token ] {}", refresh);

        Token access = TokenType.ACCESS_TOKEN.factory(user);
        log.info("[ access token ] {}", access);


        Map<String, String> data = new HashMap<>();
        data.put("refresh_token", refresh.getValue());
        data.put("access_token", access.getValue());

        eventPublisher.publishEvent(new RefreshTokenEvent(user.getId(), refresh.getValue()));

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(), ResponseEntity.ok(BodyFactory.success(data)));
    }
}
