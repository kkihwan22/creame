package today.creame.web.payments.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ReceiptRequest {
    private String membid;
    private String membnm;
    private String oid;
    private String tid;
    private int amount;
    private int coinamt;
    private String reqResult;
    private String resultmessage;
    private String paytype;
    private String telno;
}
