package today.creame.web.config.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import today.creame.web.member.domain.Token;
import today.creame.web.share.entrypoint.BodyFactory;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


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
        User user = (User) authentication.getPrincipal();
        Map<String, String> data = new HashMap<>();
        data.put("refresh_token", Token.refreshToken(user.getUsername(), user.getAuthorities()).getValue());
        data.put("access_token", Token.accessToken(user.getUsername(), user.getAuthorities()).getValue());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(
                response.getOutputStream(), ResponseEntity.ok(BodyFactory.success(data)));

    }
}
