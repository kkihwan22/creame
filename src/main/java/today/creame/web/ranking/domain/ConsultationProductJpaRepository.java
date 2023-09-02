package today.creame.web.ranking.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationProductJpaRepository extends JpaRepository<ConsultationProduct, Long> {

    List<ConsultationProduct> findConsultationProductsByRankingIdOrderByOrderNo(Long rankingId);
}
