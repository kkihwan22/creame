package today.creame.web.payments.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.coin.domain.CoinsHistoryType;
import today.creame.web.payments.application.model.PaymentHistoryResult;
import today.creame.web.payments.domain.PaymentHistoryJpaRepository;
import today.creame.web.payments.domain.PaymentsHistory;
import today.creame.web.payments.domain.PaymentsHistoryStatus;
import today.creame.web.share.support.SecurityContextSupporter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final Logger log = LoggerFactory.getLogger(PaymentQueryServiceImpl.class);
    private final PaymentHistoryJpaRepository paymentHistoryJpaRepository;
    @Override
    public List<PaymentHistoryResult> history(int since) {
        Long id = SecurityContextSupporter.getId();

        LocalDateTime sinceDateTime = LocalDate.now().minusMonths(since).atTime(LocalTime.MIDNIGHT);
        List<PaymentsHistory> result = paymentHistoryJpaRepository.findPaymentsHistoryByMember_IdAndCreatedDateTimeAfter(id, sinceDateTime);
        return result.stream()
                .filter(it -> it.getStatus() != PaymentsHistoryStatus.FAILED)
                .map(history -> new PaymentHistoryResult(history))
                .collect(Collectors.toList());
    }
}
