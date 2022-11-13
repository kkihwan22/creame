package today.creame.web.influence.domain;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import today.creame.web.influence.application.model.InfluenceResult;

public interface InfluenceJpaRepository extends JpaRepository<Influence, Long> {
    List<Influence> findAllByStatusIn(List<InfluenceStatus> status, Pageable pageable);
    List<Influence> findByIdIn(Set<Long> idSet);
}
