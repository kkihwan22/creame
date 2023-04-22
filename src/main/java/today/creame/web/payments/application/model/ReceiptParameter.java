package today.creame.web.payments.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.exception.NotFoundMemberException;
import today.creame.web.payments.domain.PaymentsHistory;
import today.creame.web.payments.domain.PaymentsHistoryType;

@Getter
@ToString
public class ReceiptParameter {
    private String membid;
    private String oid;
    private String tid;
    private int amount;
    private int coinamt;
    private String reqResult;
    private String resultmessage;
    private PaymentsHistoryType type;

    public ReceiptParameter(String membid, String oid, String tid, int amount, int coinamt, String reqResult, String resultmessage, PaymentsHistoryType type) {
        this.membid = membid;
        this.oid = oid;
        this.tid = tid;
        this.amount = amount;
        this.coinamt = coinamt;
        this.reqResult = reqResult;
        this.resultmessage = resultmessage;
        this.type = type;
    }

    public boolean failed() {
        return !reqResult.equals("0000");
    }

    public PaymentsHistory toEntity(MemberJpaRepository memberJpaRepository) {
        Member member = memberJpaRepository
            .findMemberByM2netUserId(membid)
            .orElseThrow(NotFoundMemberException::new);

        return new PaymentsHistory(member, type, oid, tid, amount, coinamt, reqResult, resultmessage);
    }

    public static ReceiptParameter paramToAutoCharging(String membid, String oid, String tid, int amount, int coinamt, String reqResult, String resultmessage) {
        if (!reqResult.equals("0000")) {
            return ReceiptParameter.paramToPaymentFailure(membid, oid, tid, amount, coinamt, reqResult, resultmessage);
        }

        return new ReceiptParameter(membid, oid, tid, amount, coinamt, reqResult, resultmessage, PaymentsHistoryType.AUTO_CHARGING);
    }

    public static ReceiptParameter paramToPaymentSuccess(String membid, String oid, String tid, int amount, int coinamt, String reqResult, String resultmessage) {
        if (!reqResult.equals("0000")) {
            return ReceiptParameter.paramToPaymentFailure(membid, oid, tid, amount, coinamt, reqResult, resultmessage);
        }

        return new ReceiptParameter(membid, oid, tid, amount, coinamt, reqResult, resultmessage, PaymentsHistoryType.PAYMENT_REQUEST);
    }

    private static ReceiptParameter paramToPaymentFailure(String membid, String oid, String tid, int amount, int coinamt, String reqResult, String resultmessage) {
        return new ReceiptParameter(membid, oid, tid, amount, coinamt, reqResult, resultmessage, PaymentsHistoryType.PAYMENT_FAILED);
    }
}
