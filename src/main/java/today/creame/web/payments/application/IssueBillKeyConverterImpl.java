package today.creame.web.payments.application;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.m2net.infra.feign.io.M2netBillKeyIssueRequest;
import today.creame.web.member.domain.Member;
import today.creame.web.payments.domain.AutoChargingPreference;
import today.creame.web.payments.domain.CreditCard;
import today.creame.web.payments.exception.IssueBillKeyException;
import today.creame.web.share.support.Crypto;
import today.creame.web.share.support.M2netCrypto;
import today.creame.web.share.support.OrderKey;

@RequiredArgsConstructor
@Service
public class IssueBillKeyConverterImpl implements IssueBillKeyConverter {
    private final Logger log = LoggerFactory.getLogger(IssueBillKeyConverter.class);
    private final OrderKey orderKey;
    private final Crypto crypto;

    public IssueBillKeyConverterImpl() throws Exception {
        this.orderKey = new OrderKey();
        this.crypto = new M2netCrypto();
    }

    @Override
    public M2netBillKeyIssueRequest convert(CreditCard card, AutoChargingPreference preference, Member member) {
        try {
            Optional<AutoChargingPreference> optional = Optional.ofNullable(preference);
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
                .amount(optional.map(it -> it.paymentAmount()).orElseGet(() -> 1000))
                .coinamt(optional.map(it -> it.chargeCoins()).orElseGet(() -> 1000))
                .build();
        } catch (Exception e) {
            log.error("빌키 발급 중 에러가 발생했습니다.\n error : {}", e);
            throw new IssueBillKeyException();
        }
    }
}
