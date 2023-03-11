package today.creame.web.payments.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentBillKeyJpaRepository extends JpaRepository<PaymentBillKey, Long> {

    Optional<PaymentBillKey> findPaymentBillKeyByMemberIdAndDeleted(Long memberId, boolean deleted);

    Boolean existsPaymentBillKeyByMemberIdAndDeleted(Long memberId, boolean deleted);
}
