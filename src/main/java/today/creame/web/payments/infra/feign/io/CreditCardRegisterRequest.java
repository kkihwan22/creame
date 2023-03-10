package today.creame.web.payments.infra.feign.io;

import lombok.Getter;
import lombok.ToString;

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

}
