package today.creame.web.payments.domain;

import javax.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class CreditCard {

    @Column(name = "card_no", columnDefinition = "CHAR(16)")
    private String cardno;

    @Column(name = "exp_year", columnDefinition = "CHAR(2)")
    private String expYear;

    @Column(name = "exp_month", columnDefinition = "CHAR(2)")
    private String expMonth;

    @Column(name = "serial")
    private String serial;

    @Column(name = "card_password", columnDefinition = "CHAR(2)")
    private String cardPassword;

    @Column(name = "payment_password", columnDefinition = "CHAR(6)")
    private String paymentPassword;

    public CreditCard(String cardno, String expYear, String expMonth, String serial, String cardPassword, String paymentPassword) {
        this.cardno = cardno;
        this.expYear = expYear;
        this.expMonth = expMonth;
        this.serial = serial;
        this.cardPassword = cardPassword;
        this.paymentPassword = paymentPassword;
    }

    public CreditCard(CreditCard other, String paymentPassword) {
        this(other.cardno, other.expYear, other.expMonth, other.serial, other.cardPassword, paymentPassword);
    }

    public String maskCardno() {
        String substring = this.cardno.substring(0, 8);
        return substring.concat("********");
    }

    public boolean isMatchedPassword(String password) {
        return this.paymentPassword.equals(password);
    }
}
