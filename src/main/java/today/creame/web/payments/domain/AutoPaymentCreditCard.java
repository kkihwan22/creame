package today.creame.web.payments.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "auto_payment_credit_card")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class AutoPaymentCreditCard extends BaseCreatedAndUpdatedDateTime {
    private final static double BENEFIT_RATE = 0.01;
    private final static double VAT = 0.1;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "bill_key")
    private String billKey;

    @Column(name = "balance")
    private int balance;

    @Column(name = "charging_amt")
    private int chargingAmount;

    @Column(name = "deleted")
    private boolean deleted;

//    public int requestChargingAmount() {
//        return (int) (this.chargingAmount + (chargingAmount * VAT));
//    }
//
//    public int requestChargingCoins() {
//
//        return (int) (this.chargingAmount + (chargingAmount * BENEFIT_RATE));
//    }
}
