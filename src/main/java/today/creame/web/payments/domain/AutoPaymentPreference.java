package today.creame.web.payments.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = PROTECTED)
@Embeddable
@Getter
@ToString
public class AutoPaymentPreference {
    private final static double BENEFIT_RATE = 0.01;
    private final static double VAT = 0.1;

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