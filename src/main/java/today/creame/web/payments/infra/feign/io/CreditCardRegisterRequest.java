package today.creame.web.payments.infra.feign.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;
import today.creame.web.payments.domain.AutoChargingPreference;
import today.creame.web.payments.domain.CreditCard;
import today.creame.web.share.support.Crypto;
import today.creame.web.share.support.OrderKey;

@Getter
@ToString
public class CreditCardRegisterRequest {
    private String oid;
    private String cardno;
    private String expMonth;
    private String expYear;
    private String socno;
    private String password;
    private String usernm;
    private String item;
    private int amount;
    private int coinamt;
    private String membid;
    private String telno;
    private String pushurl;

    public CreditCardRegisterRequest(Crypto crypto, OrderKey key, CreditCard card, String item, AutoChargingPreference preference, Member member, String pushurl) throws Exception {
        this.oid = key.next();
        this.cardno = crypto.encrypt(card.getCardno());
        this.expMonth = crypto.encrypt(card.getExpMonth());
        this.expYear = crypto.encrypt(card.getExpYear());
        this.socno = crypto.encrypt(card.getSocno());
        this.usernm = crypto.encrypt(card.getUsernm());
        this.password = crypto.encrypt(card.getPassword());
        this.item = item;
        this.amount = preference.requestChargingAmount();
        this.coinamt = preference.requestChargingCoins();
        this.membid = crypto.encrypt(member.getM2netUserId());
        this.telno = crypto.encrypt(member.getPhoneNumber());
        this.pushurl = pushurl;
    }
}
