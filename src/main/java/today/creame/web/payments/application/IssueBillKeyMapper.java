package today.creame.web.payments.application;

import org.springframework.stereotype.Component;
import today.creame.web.m2net.infra.feign.io.M2netBillKeyIssueRequest;
import today.creame.web.member.domain.Member;
import today.creame.web.payments.domain.CreditCard;
import today.creame.web.share.support.Crypto;
import today.creame.web.share.support.M2netCrypto;
import today.creame.web.share.support.OrderKey;

@Component
public class IssueBillKeyMapper {
    private final static String ITEM_NM = "크리미 서비스 자동결제";
    private final static String PUSH_URL = "";
    private final OrderKey orderKey;
    private final Crypto crypto;

    public IssueBillKeyMapper() throws Exception {
        this.orderKey = new OrderKey();
        this.crypto = new M2netCrypto();
    }

    public M2netBillKeyIssueRequest mapping(CreditCard card, Member member) throws Exception {
        return M2netBillKeyIssueRequest.builder()
            .oid(orderKey.next())
            .cardno(crypto.encrypt(card.getCardno()))
            .expMonth(crypto.encrypt(card.getExpMonth()))
            .expYear(crypto.encrypt(card.getExpYear()))
            .socno(crypto.encrypt(card.getSerial()))
            .pass(crypto.encrypt(card.getCardPassword()))
            .usernm(crypto.encrypt(member.getNickname()))
            .item(ITEM_NM)
            .membid(member.getM2netUserId())
            .telno(member.getPhoneNumber())
            .pushurl(PUSH_URL)
            .build();
    }
}