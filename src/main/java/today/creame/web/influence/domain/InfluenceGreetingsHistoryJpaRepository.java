package today.creame.web.influence.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluenceGreetingsHistoryJpaRepository extends JpaRepository<InfluenceGreetingsHistory, Long> {

}