package today.creame.web.influence.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluenceGreetingsHistoryJpaRepository extends JpaRepository<InfluenceGreetingsHistory, Long> {
    List<InfluenceGreetingsHistory> findByInfluence_IdAndStatusOrderByUpdatedByDesc(Long influenceId, GreetingsProgressStatus status);
}
