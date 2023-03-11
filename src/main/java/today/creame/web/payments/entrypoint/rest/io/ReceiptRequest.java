package today.creame.web.payments.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReceiptRequest {
    private String membid;
    private String oid;
    private String tid;
    private int amount;
    private int coinamt;
    private String reqResult;
    private String resultmessage;

    public ReceiptRequest(String membid, String oid, String tid, int amount, int coinamt, String reqResult, String resultmessage) {
        this.membid = membid;
        this.oid = oid;
        this.tid = tid;
        this.amount = amount;
        this.coinamt = coinamt;
        this.reqResult = reqResult;
        this.resultmessage = resultmessage;
    }
}
