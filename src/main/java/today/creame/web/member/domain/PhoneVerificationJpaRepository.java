package today.creame.web.member.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneVerificationJpaRepository extends JpaRepository<PhoneVerification, Long> {

    Optional<PhoneVerification> findByPhoneNumber(String phoneNumber);

    Optional<PhoneVerification> findPhoneVerificationByPhoneNumberAndToken(String phoneNumber, Long token);

    Optional<PhoneVerification> findByToken(String token);

    Optional<PhoneVerification> findTopByTokenOrderByCreatedDateTimeDesc(Long token);

    List<PhoneVerification> findPhoneVerificationsByTokenAndVerified(String token, boolean authed);

    List<PhoneVerification> findPhoneVerificationsByPhoneNumberAndVerifiedAndCreatedDateTimeAfter(
        String phoneNumber,
        boolean authed,
        LocalDateTime date
    );
}
