package today.creame.web.payments.application;

import org.springframework.stereotype.Component;
import today.creame.web.m2net.infra.feign.io.M2netBillKeyIssueRequest;
import today.creame.web.member.domain.Member;
import today.creame.web.payments.domain.AutoChargingPreference;
import today.creame.web.payments.domain.CreditCard;

@Component
public interface IssueBillKeyConverter {
    String ITEM_NM = "크리미 서비스 자동결제";
    String PUSH_URL = "";

    M2netBillKeyIssueRequest convert(CreditCard card, AutoChargingPreference preference, Member member);
}