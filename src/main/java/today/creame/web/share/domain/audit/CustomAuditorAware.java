package today.creame.web.share.domain.audit;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@Component
public class CustomAuditorAware implements AuditorAware<Long> {
    private final Logger log = LoggerFactory.getLogger(CustomAuditorAware.class);

    @Override
    public Optional<Long> getCurrentAuditor() {
        CustomUserDetails principal = SecurityContextSupporter.get();
        return Optional.ofNullable(principal.getId());
    }
}