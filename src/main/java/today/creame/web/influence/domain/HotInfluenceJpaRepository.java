package today.creame.web.influence.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotInfluenceJpaRepository extends JpaRepository<HotInfluence, Long> {

    List<HotInfluence> findHotInfluencesByEnabledOrderByOrderNumberAsc(boolean enabled);
    List<HotInfluence> findAllByEnabled(Boolean enabled);
    Optional<HotInfluence> findByInfluenceId(Long influenceId);

}
