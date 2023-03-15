package today.creame.web.member.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
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
import today.creame.web.member.exception.AlreadyExpiredTokenException;
import today.creame.web.member.exception.AlreadyVerifiedTokenException;
import today.creame.web.member.exception.ExceededFailedCountException;
import today.creame.web.member.exception.NotMatchedCodeException;
import today.creame.web.member.exception.NotMatchedPhoneNumberException;

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

    @Column(
        name = "digit",
        columnDefinition = "char(6)"
    )
    private Integer digit;

    @Column(
        name = "token",
        columnDefinition = "char(10)"
    )
    private Long token;

    @Column(name = "authed",
            columnDefinition = "BIT",
            length = 1
    )
    private boolean verified;

    @Column(
        name = "expired",
        columnDefinition = "BIT",
        length = 1
    )
    private boolean expired;

    @Column(name = "failed_cnt",
            columnDefinition = "tinyint")
    private int failedCount;

    @Column(name = "verified_dt")
    private LocalDateTime verifiedDateTime;

    @CreatedDate
    @Column(name = "created_dt")
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(name = "updated_dt")
    private LocalDateTime updatedDateTime;

    public void verity(String phoneNumber, int digit) {
        if (isExpired()) {
            throw new AlreadyExpiredTokenException();
        }

        if (isVerified()) {
            throw new AlreadyVerifiedTokenException();
        }

        if (this.afterVerifyTime()) {
            throw new AlreadyExpiredTokenException();
        }

        if (!this.matchedDigit(digit)) {
            throw new NotMatchedCodeException(this.failedCount);
        }

        if (!this.phoneNumber.equals(phoneNumber)) {
            throw new NotMatchedPhoneNumberException();
        }

        if (failedCount > 3) {
            throw new ExceededFailedCountException();
        }

        this.verified = true;
        this.verifiedDateTime = LocalDateTime.now();
    }

    public boolean isValid() {
        if (afterVerifyTime()) return false;
        if (failedCount > 5) return false;
        if (verified) return false;
        return true;
    }

    private boolean afterVerifyTime() {
        LocalDateTime now = LocalDateTime.now();
        if (this.createdDateTime != null && this.createdDateTime.isAfter(now.plusMinutes(3))) {
            this.expired = true;
        }

        return expired;
    }

    public boolean matchedDigit(int digit) {
        if (this.digit != digit) {
            this.failedCount++;
            return false;
        }

        if (failedCount >= 3) {
            this.expired = true;
            return false;
        }

        return true;
    }

    public PhoneVerification(String phoneNumber, Integer digit, Long token) {
        this.phoneNumber = phoneNumber;
        this.digit = digit;
        this.token = token;
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
