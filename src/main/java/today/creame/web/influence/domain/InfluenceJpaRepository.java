package today.creame.web.influence.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluenceJpaRepository extends JpaRepository<Influence, Long> {
    List<Influence> findAllByBlocked(Boolean blocked, Pageable pageable);

    List<Influence> findInfluencesByBlockedAndExposed(boolean blocked, boolean exposed, Pageable pageable);

    List<Influence> findInfluencesByIdIn(Set<Long> idSet);

    Optional<Influence> findInfluenceByM2NetCounselorId(String cid);

    List<Influence> findInfluencesByIdInAndConnectedIs(Set<Long> idSet, Boolean connected);
}
