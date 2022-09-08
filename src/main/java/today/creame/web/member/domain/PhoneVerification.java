package today.creame.web.member.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.convert.BooleanToCharConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "phone_verification")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class PhoneVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", columnDefinition = "char(11)")
    private String phoneNumber;

    @Column(name = "digit")
    private Integer digit;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "authed",
            columnDefinition = "char(1)")
    private Boolean authed;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "expired",
            columnDefinition = "char(1)")
    private Boolean expired;

    @Column(name = "failed_cnt",
            columnDefinition = "tinyint")
    private int failedCount;

    @Column(name = "expired_dt")
    private LocalDateTime expiredDateTime;

    @Column(name = "created_dt")
    private LocalDateTime createdDateTime;

    @Column(name = "updated_dt")
    private LocalDateTime updatedDateTime;

    public PhoneVerification(String phoneNumber, Integer digit) {
        this.phoneNumber = phoneNumber;
        this.digit = digit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        PhoneVerification item = (PhoneVerification) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
