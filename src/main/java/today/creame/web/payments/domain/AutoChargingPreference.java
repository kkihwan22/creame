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

    public AutoChargingPreference(boolean enabled, int balance, int amount) {
        this.enabled = enabled;
        this.balance = balance;
        this.amount = amount;
    }

    public int paymentAmount() {
        return (int) (this.amount + (amount * VAT));
    }

    public int chargeCoins() {
        return (int) (this.amount + (amount * BENEFIT_RATE));
    }

    public static AutoChargingPreference init(int amount) {
        return new AutoChargingPreference(true, 10000, amount);
    }
}