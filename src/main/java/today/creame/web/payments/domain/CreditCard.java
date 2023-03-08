package today.creame.web.payments.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreditCard {
    private String cardno;
    private String expYear;
    private String expMonth;
    private String socno;
    private String password;
    private String usernm;

    public CreditCard(String cardno, String expYear, String expMonth, String socno, String password, String usernm) {
        this.cardno = cardno;
        this.expYear = expYear;
        this.expMonth = expMonth;
        this.socno = socno;
        this.password = password;
        this.usernm = usernm;
    }
}
