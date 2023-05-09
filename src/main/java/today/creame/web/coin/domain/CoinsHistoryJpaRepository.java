package today.creame.web.coin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CoinsHistoryJpaRepository extends JpaRepository<CoinsHistory, Long> {

    List<CoinsHistory> findCoinsHistoryByMember_IdAndCreatedDateTimeAfter(Long memberId, LocalDateTime since);
}
