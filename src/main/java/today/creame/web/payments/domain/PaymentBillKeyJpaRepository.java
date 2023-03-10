package today.creame.web.payments.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentBillKeyJpaRepository extends JpaRepository<PaymentBillKey, Long> {

    Boolean existsPaymentBillKeyByMemberIdAAndDeleted(Long memberId, boolean deleted);
}
