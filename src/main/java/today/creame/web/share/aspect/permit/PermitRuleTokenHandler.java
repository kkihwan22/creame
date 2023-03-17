package today.creame.web.share.aspect.permit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import today.creame.web.member.application.PhoneVerificationService;
import today.creame.web.member.exception.NotMatchedTokenException;
import today.creame.web.share.support.ApplicationContextSupporter;

public class PermitRuleTokenHandler implements PermitRuleHandler {
    private final Logger log = LoggerFactory.getLogger(PermitRuleTokenHandler.class);

    @Override
    public void handle(Object param) {
        PhoneVerificationService bean = (PhoneVerificationService) ApplicationContextSupporter.getBean("phoneVerificationServiceImpl");
        if (!bean.isVerified(param.toString())) {
            log.debug("유효하지 않는 토큰 정보입니다.");
            throw new NotMatchedTokenException();
        }
    }
}