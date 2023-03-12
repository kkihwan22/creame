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

    public ReceiptParameter(String membid, String oid, String tid, int amount, int coinamt, String reqResult, String resultmessage) {
        this.membid = membid;
        this.oid = oid;
        this.tid = tid;
        this.amount = amount;
        this.coinamt = coinamt;
        this.reqResult = reqResult;
        this.resultmessage = resultmessage;
    }

    public PaymentsHistory toEntity(MemberJpaRepository memberJpaRepository) {
        Member member = memberJpaRepository
            .findMemberByM2netUserId(membid)
            .orElseThrow(NotFoundMemberException::new);

        return new PaymentsHistory(
            member,
            !reqResult.equals("00") ? PaymentsHistoryType.PAYMENT_FAILED : PaymentsHistoryType.AUTO_CHARGING, oid, tid, amount, coinamt, reqResult, resultmessage
        );
    }
}
