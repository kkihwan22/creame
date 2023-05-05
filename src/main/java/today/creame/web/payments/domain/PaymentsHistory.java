package today.creame.web.payments.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.member.domain.Member;
import today.creame.web.payments.domain.converter.PaymentMethodToStringConverter;
import today.creame.web.payments.domain.converter.PaymentsHistoryTypeToStringConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

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
    @Column(name = "status")
    private PaymentsHistoryStatus status;

    @Convert(converter = PaymentMethodToStringConverter.class)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

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

    @Column(name = "canceled_dt")
    private LocalDateTime canceledDateTime;

    public void canceled() {
        this.status = PaymentsHistoryStatus.CANCELED;
        this.canceledDateTime = LocalDateTime.now();
    }

    public PaymentsHistory(Member member, PaymentsHistoryStatus status, String orderId, String txId, int amount, int coins, PaymentMethod paymentMethod, String resultCode, String resultMessage) {
        this.member = member;
        this.status = status;
        this.orderId = orderId;
        this.txId = txId;
        this.amount = amount;
        this.coins = coins;
        this.paymentMethod = paymentMethod;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public PaymentsHistory(Member member, PaymentsHistoryStatus status, String orderId, String txId, int amount, int coins, PaymentMethod paymentMethod) {
        this(member, status, orderId, txId, amount, coins, paymentMethod, null, null);
    }
}
