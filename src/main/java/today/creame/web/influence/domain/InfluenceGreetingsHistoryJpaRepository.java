package today.creame.web.influence.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfluenceGreetingsHistoryJpaRepository extends JpaRepository<InfluenceGreetingsHistory, Long> {
    Optional<InfluenceGreetingsHistory> findTopByInfluence_IdAndStatusInOrderByUpdatedDateTimeDesc(Long influenceId, List<GreetingsProgressStatus> status);
}
