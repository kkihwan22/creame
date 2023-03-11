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
import today.creame.web.member.domain.Member;
import today.creame.web.payments.exception.NotMatchedPasswordException;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "payment_bill_key")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class PaymentBillKey extends BaseCreatedAndUpdatedDateTime {
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
}
