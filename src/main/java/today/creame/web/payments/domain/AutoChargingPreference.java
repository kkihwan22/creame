package today.creame.web.payments.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = PROTECTED)
@Embeddable
@Getter
@ToString
public class AutoChargingPreference {
    private final static double BENEFIT_RATE = 0.01;
    private final static double VAT = 0.1;

    @Column(name = "auto_charging_enabled")
    private boolean enabled;

    @Column(name = "balance")
    private int balance;

    @Column(name = "amount")
    private int amount;

//    public AutoPaymentPreference(int balance, int chargingAmount) {
//        this.balance = balance;
//        this.chargingAmount = chargingAmount;
//    }
//
//    public int requestChargingAmount() {
//        return (int) (this.chargingAmount + (chargingAmount * VAT));
//    }
//
//    public int requestChargingCoins() {
//
//        return (int) (this.chargingAmount + (chargingAmount * BENEFIT_RATE));
//    }
}