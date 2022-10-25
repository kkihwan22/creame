package today.creame.web.member.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import today.creame.web.share.domain.converter.BooleanToCharConverter;

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
    private Boolean verified;

    @Convert(converter = BooleanToCharConverter.class)
    @Column(name = "expired",
            columnDefinition = "char(1)")
    private Boolean expired;

    @Column(name = "failed_cnt",
            columnDefinition = "tinyint")
    private int failedCount;

    @Column(name = "expired_dt")
    private LocalDateTime expiredDateTime;

    @CreatedDate
    @Column(name = "created_dt")
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(name = "updated_dt")
    private LocalDateTime updatedDateTime;

    public boolean afterVerifyTime() {
        LocalDateTime now = LocalDateTime.now();
        if (this.createdDateTime.isAfter(now.plusMinutes(3))) {
            this.expired = true;;
            this.expiredDateTime = now;
        }

        return expired;
    }

    public boolean isMissMatchDigit(int digit) {
        if (this.digit == digit) {
            return false;
        }

        this.failedCount++;
        if (failedCount >= 3) {
            this.expired = true;
            this.expiredDateTime = LocalDateTime.now();
        }
        return true;
    }

    public void successVerify() {
        this.verified = true;
    }

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
