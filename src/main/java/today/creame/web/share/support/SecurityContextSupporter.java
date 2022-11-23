package today.creame.web.share.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import today.creame.web.config.security.CustomUserDetails;

public class SecurityContextSupporter {
    private final static Logger log = LoggerFactory.getLogger(SecurityContextSupporter.class);

    public static CustomUserDetails get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            log.error("[ error ] 로그인 되지 않았습니다. 확인 부탁드립니다.");
            // todo: exception ?!?
            return null;
        }
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        log.debug("principal: {}", principal);
        return principal;
    }
}
