package today.creame.web.payments.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryJpaRepository extends JpaRepository<PaymentsHistory, Long> {

}
