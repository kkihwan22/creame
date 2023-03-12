package today.creame.web.payments.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.member.domain.Member;
import today.creame.web.payments.domain.converter.PaymentsHistoryTypeToStringConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "payments_history")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class PaymentsHistory extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Convert(converter = PaymentsHistoryTypeToStringConverter.class)
    @Column(name = "type")
    private PaymentsHistoryType type;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "tx_id")
    private String txId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "coins")
    private int coins;

    @Column(name = "result_code")
    private String resultCode;

    @Column(name = "result_message")
    private String resultMessage;

    public PaymentsHistory(Member member, PaymentsHistoryType type, String orderId, String txId, int amount, int coins, String resultCode, String resultMessage) {
        this.member = member;
        this.type = type;
        this.orderId = orderId;
        this.txId = txId;
        this.amount = amount;
        this.coins = coins;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
