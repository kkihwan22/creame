package today.creame.web.payments.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import today.creame.web.m2net.infra.feign.M2netClient;
import today.creame.web.m2net.infra.feign.io.M2netPaymentRequest;
import today.creame.web.m2net.infra.feign.io.M2netSimpleResponse;
import today.creame.web.member.domain.Member;
import today.creame.web.payments.exception.NotMatchedPasswordException;
import today.creame.web.payments.exception.PaymentFailureException;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "payment_bill_key")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class PaymentBillKey extends BaseCreatedAndUpdatedDateTime {
    private final static Logger log = LoggerFactory.getLogger(PaymentBillKey.class);
    private final static double BENEFIT_RATE = 0.01;
    private final static double VAT = 0.1;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "bill_key")
    private String billKey;

    @Embedded
    private CreditCard creditCard;

    @Embedded
    private AutoChargingPreference preference;

    @Column(name = "deleted")
    private boolean deleted;

    public PaymentBillKey(Member member, String billKey, CreditCard creditCard) {
        this.memberId = member.getId();
        this.billKey = billKey;
        this.creditCard = creditCard;
    }

    public boolean isEnabledAutoCharging() {
        return preference != null && preference.isEnabled();
    }

    public void enabledAutoCharging(AutoChargingPreference preference) {
        this.preference = preference;
    }

    public void disabledAutoCharging() {
        this.preference = null;
    }

    public void updateBillKey(String billKey) {
        this.billKey = billKey;
    }

    public void changePassword(String oPass, String nPass) {
        if (!this.creditCard.isMatchedPassword(oPass)) {
            throw new NotMatchedPasswordException();
        }

        this.creditCard = new CreditCard(this.creditCard, nPass);
    }

    public void pay(String password, int amount, Member member, M2netClient client) {
        if (!this.creditCard.isMatchedPassword(password)) {
            log.error("credit card password: {}, requested password: {}", this, creditCard.getPaymentPassword(), password);
            throw new NotMatchedPasswordException();
        }

        int applyVatAmount = (int) (amount + (amount * 0.1));
        M2netSimpleResponse body = client.requestPayment(new M2netPaymentRequest(member.getM2netUserId(), applyVatAmount, amount)).getBody();

        if (!body.getReqResult().equals("00")) {
            throw new PaymentFailureException();
        }
    }

    public void delete() {
        this.deleted = true;
    }
}
