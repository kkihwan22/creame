package today.creame.web.share.aspect.permit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.share.aspect.permit.exception.NoPermitException;
import today.creame.web.share.support.SecurityContextSupporter;

public class PermitRuleMeHandler implements PermitRuleHandler {
    private final static Logger log = LoggerFactory.getLogger(PermitRuleMeHandler.class);

    @Override
    public void handle(Object param) {
        CustomUserDetails customUserDetails = SecurityContextSupporter.get();
        if (!customUserDetails.getId().equals((Long) param)) {
            log.info("본인이 아니면 접근할 수 없습니다.");
            throw new NoPermitException();
        }
    }
}
