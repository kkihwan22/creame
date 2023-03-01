package today.creame.web.config.security;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import today.creame.web.config.security.exception.SecurityErrorCode;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;
import today.creame.web.share.exception.BusinessException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(ExceptionHandlerFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        log.info("--------------------------------");
        log.info("[ filter ] START.");
        log.info("token: {} ", request.getHeader("Authorization"));
        log.info("URL: {}", request.getRequestURI());
        log.info("--------------------------------");

        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e) {
            logger.info("토큰의 유효기간이 만료되었습니다.");
            this.writeValue(response, UNAUTHORIZED, e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            logger.info("인증 중 알수 없는 에러가 발생했습니다. reason : {}", e);
            this.writeValue(response, BAD_REQUEST, SecurityErrorCode.UNKNOWN_AUTH.getCode(), SecurityErrorCode.UNKNOWN_AUTH.getMessage());
        }
    }

    private void writeValue(HttpServletResponse response, HttpStatus status, int errorCode, String errorMessage) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Body<SimpleBodyData<String>> body = BodyFactory.failure(
            errorCode,
            errorMessage,
            "",
            new SimpleBodyData<>("error"));

        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}
