package today.creame.web.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;
import today.creame.web.config.security.exception.SecurityErrorCode;
import today.creame.web.config.security.exception.TokenExpiredException;
import today.creame.web.config.security.exception.UnauthorizationException;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class SecurityErrorHandleFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(SecurityErrorHandleFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.debug("[ filter ] execute error filter.");

        try {
            filterChain.doFilter(request, response);
        } catch (TokenExpiredException e) {
            logger.info("토큰의 유효기간이 만료되었습니다.");
            this.writeValue(response, UNAUTHORIZED, SecurityErrorCode.EXPIRED_TOKEN);

        } catch (UnauthorizationException e) {
            logger.info("유효하지 않는 토큰입니다. 원인 -[{}]", e);
            this.writeValue(response, UNAUTHORIZED, SecurityErrorCode.INVALID_TOKEN);

        } catch (RuntimeException e) {
            logger.info("인증 중 알수 없는 에러가 발생했습니다. reason : {}", e);
            this.writeValue(response, BAD_REQUEST, SecurityErrorCode.UNKNOWN_AUTH);
        }
    }

    private void writeValue(HttpServletResponse response, HttpStatus status, SecurityErrorCode error) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Body<SimpleBodyData<String>> body = BodyFactory.failure(
                error.getCode(),
                error.getMessage(),
                "",
                new SimpleBodyData<>("error"));
        new ObjectMapper().writeValue(response.getOutputStream(), ResponseEntity.of(Optional.of(body)));
    }
}
