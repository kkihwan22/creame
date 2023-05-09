package today.creame.web.payments.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentHistoryJpaRepository extends JpaRepository<PaymentsHistory, Long> {

    Optional<PaymentsHistory> findByOrderId(String oid);
    List<PaymentsHistory> findPaymentsHistoryByMember_IdAndCreatedDateTimeAfter(Long id, LocalDateTime since);
}
