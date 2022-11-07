package today.creame.web.share.domain.audit;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import today.creame.web.config.security.CustomUserDetails;

@RequiredArgsConstructor
@Component
public class CustomAuditorAware implements AuditorAware<Long> {
    private final Logger log = LoggerFactory.getLogger(CustomAuditorAware.class);

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            log.error("[ error ] 로그인 되지 않았습니다. 확인 부탁드립니다.");
            // todo: exception ?!?
            return null;
        }
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        log.debug("principal: {}", principal);
        return Optional.ofNullable(principal.getId());
    }
}