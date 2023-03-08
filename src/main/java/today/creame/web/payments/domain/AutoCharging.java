package today.creame.web.payments.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "auto_charging")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class AutoCharging extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "password", columnDefinition = "CHAR(6)")
    private String password; // TODO: 암호화

    @Column(name = "bill_key")
    private String billKey;

    @Embedded
    private AutoChargingPreference preference;

    @Column(name = "deleted")
    private boolean deleted;
}
