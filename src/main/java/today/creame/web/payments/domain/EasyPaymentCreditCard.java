package today.creame.web.payments.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "easy_payment_credit_card")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class EasyPaymentCreditCard extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "bill_key")
    private String billKey;

    @Column(name = "password", columnDefinition = "CHAR(6)")
    private String password;

    @Column(name = "deleted")
    private boolean deleted;
}
