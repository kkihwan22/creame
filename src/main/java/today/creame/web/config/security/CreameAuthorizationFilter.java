package today.creame.web.config.security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import today.creame.web.config.security.exception.InvalidTokenException;
import today.creame.web.config.security.exception.TokenNotExistException;
import today.creame.web.member.domain.Token;
import today.creame.web.member.domain.TokenType;
import today.creame.web.member.domain.TokenVerified;
import today.creame.web.share.support.BearerTokenSupporter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@Component
public class CreameAuthorizationFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(CreameAuthorizationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = Optional
            .ofNullable(request.getHeader(AUTHORIZATION))
            .orElseGet(() -> null);

        String servletPath = request.getServletPath();

        if (authorizationHeader == null) {
            if (!(servletPath.startsWith("/public")
                || servletPath.startsWith("/pages")
                || servletPath.startsWith("/_health")
                || servletPath.startsWith("/swagger-ui")
                || servletPath.startsWith("/v3/api-docs/")
                || servletPath.startsWith("/favicon")
                    || servletPath.startsWith("/m2net/payments")
            )

            ) {
                throw new TokenNotExistException();
            }
        } else {
            if (!servletPath.startsWith("/m2net")) {
                String key = BearerTokenSupporter.extract(authorizationHeader);
                log.debug("[access token] : {}", key);

                Token requestToken = new Token(key, TokenType.ACCESS_TOKEN);
                TokenVerified verify = requestToken.verify();

                if (!verify.isVerified()) {
                    log.info("token invalid. value:{}", key);
                    throw new InvalidTokenException();
                }

                UserDetails userDetails = verify.getUserDetails();
                log.debug("[ UserDetails ] {}", userDetails);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}